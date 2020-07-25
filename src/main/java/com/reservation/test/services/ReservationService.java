package com.reservation.test.services;

import com.reservation.test.entities.Reservation;
import com.reservation.test.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public void create(Reservation reservation) {
        if (true) {
            reservationRepository.save(reservation);
        } else throw new BadRequestException();
    }

    public Reservation update(Reservation updatedReservation) {
        if (true) {
            return reservationRepository.save(updatedReservation);
        } else throw new BadRequestException();
    }

    public List<Reservation> get() {
        return reservationRepository.findAll();
    }

    public void deleteById(Integer id) {
        reservationRepository.deleteById(id);
    }
}
