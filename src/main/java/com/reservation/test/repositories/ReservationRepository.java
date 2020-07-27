package com.reservation.test.repositories;

import com.reservation.test.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT CASE WHEN count(r) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Reservation r " +
            "WHERE r.roomId = ?1 " +
            "AND r.startDate > ?2 AND r.startDate < ?3 " +
            "OR r.endDate > ?2 AND r.endDate < ?3 " +
            "OR r.startDate < ?2 AND r.endDate > ?3")
    boolean existsByRoomIdAndDate
            (Integer roomId, Instant startDate, Instant endDate);

    @Query(value = "SELECT CASE WHEN count(r) > 0 THEN TRUE ELSE FALSE END   " +
            "FROM Reservation r " +
            "WHERE r.personId = ?1 " +
            "AND r.startDate > ?2 AND r.startDate < ?3 " +
            "OR r.endDate > ?2 AND r.endDate < ?3 " +
            "OR r.startDate < ?2 AND r.endDate > ?3")
    boolean existsByPersonIdAndDate
            (Integer personId, Instant startDate, Instant endDate);

    @Query(value = "SELECT r FROM Reservation r WHERE r.startDate < ?1 AND r.endDate > ?1")
    List<Reservation> findCurrentOperations(Instant time);

    @Query( value = "SELECT r " +
            "FROM Reservation  r " +
            "WHERE r.startDate > ?1 AND r.startDate < ?2 " +
            "OR r.endDate >?1 AND r.endDate < ?2" +
            " AND r.startDate < ?1 AND r.endDate > ?2")
    List<Reservation> findByPeriod(Instant start, Instant end);
}
