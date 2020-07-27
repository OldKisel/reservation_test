package com.reservation.test.services;

import com.reservation.test.entities.Reservation;
import com.reservation.test.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;

public class ReservationServiceTests {
    ReservationService reservationService;
    ReservationRepository reservationRepository;
    RoomService roomService;
    PersonService personService;

    @BeforeEach
    public void init() {
        roomService = Mockito.mock(RoomService.class);
        personService = Mockito.mock(PersonService.class);
        reservationRepository = Mockito.mock(ReservationRepository.class);
        reservationService = new ReservationService(reservationRepository, roomService, personService);
    }

    @Test
    public void createTest() {
        Reservation newReservation = new Reservation(null,
                1,
                1,
                "operation",
                "description",
                Instant.parse("2017-08-14T12:17:47Z"),
                Instant.parse("2017-08-14T12:22:47Z"));


        reservationService.create(newReservation);
    }
}
