package com.reservation.test.services;

import com.reservation.test.entities.Reservation;
import com.reservation.test.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.time.Instant;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final PersonService personService;


    @Autowired
    ReservationService(ReservationRepository reservationRepository,
                       RoomService roomService, PersonService personService) {
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
        this.personService = personService;
    }

    public void create(Reservation res) {
        Instant start = res.getStartDate();
        Instant end = res.getEndDate();
        Integer roomId = res.getRoomId();
        Integer personId = res.getPersonId();

        roomService.checkExistsById(roomId);
        personService.checkExistsById(personId);

        if (reservationRepository.
                existsReservationByRoomIdAndDate(roomId, start, end)) {
            throw new BadRequestException(String.format(
                    "Room with id %s is already reserved on this time", roomId));
        }
        if (reservationRepository.
                existsReservationByPersonIdAndDate(personId, start, end)) {
            throw new BadRequestException(String.format(
                    "Person with id %s is already reserved room on this time", personId));
        }
        reservationRepository.save(res);
    }

    @CachePut(value = "reservations", key = "updatedReservation.id")
    public Reservation update(Reservation updatedReservation) {
        Integer id = updatedReservation.getId();

        checkExistsById(id);

        return reservationRepository.save(updatedReservation);
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Cacheable(value = "reservations")
    public List<Reservation> getCurrent(Instant currentTime) {
        return reservationRepository.findCurrentOperations(currentTime);
    }

    public List<Reservation> getActualForPeriod(Instant start, Instant end) {
        return reservationRepository.findByPeriod(start, end);
    }

    @CacheEvict(value = "reservations")
    public void deleteById(Integer id) {
        checkExistsById(id);

        reservationRepository.deleteById(id);
    }

    private void checkExistsById(Integer id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new BadRequestException(String.format("Reservation with id %s is not found", id));
        }
    }
}
