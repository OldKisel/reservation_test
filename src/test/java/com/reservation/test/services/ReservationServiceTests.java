package com.reservation.test.services;

import com.reservation.test.entities.Reservation;
import com.reservation.test.repositories.ReservationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    Instant start = Instant.parse("2017-08-14T12:17:47Z");
    Instant end = Instant.parse("2017-08-14T12:22:47Z");

    Reservation newReservation = new Reservation(null,
            1,
            1,
            "operation",
            "description",
            start,
            end);

    Reservation createdReservation = new Reservation(1,
            1,
            1,
            "operation",
            "description",
            start,
            end);

    Reservation updatedReservation = new Reservation(1,
            1,
            1,
            "therapy",
            "",
            start,
            end);

    public List<Reservation> getList() {
        List<Reservation> list = new ArrayList<>();
        list.add(createdReservation);
        list.add(updatedReservation);
        return list;
    }

    @Test
    public void createTest() {
        Mockito.doNothing().when(personService).checkExistsById(1);
        Mockito.doNothing().when(roomService).checkExistsById(1);

        Mockito.when(reservationRepository.existsByRoomIdAndDate(1, start, end)).thenReturn(false);
        Mockito.when(reservationRepository.existsByPersonIdAndDate(1, start, end)).thenReturn(false);

        reservationService.create(newReservation);

        Mockito.verify(reservationRepository, Mockito.times(1)).save(newReservation);
    }

    @Test
    public void createTestWithExceptionWhenCheckPerson() {
        Mockito.doThrow(NotFoundException.class).when(personService).checkExistsById(1);
        Mockito.doNothing().when(roomService).checkExistsById(1);

        Assertions.assertThrows(NotFoundException.class, () -> reservationService.create(newReservation));

    }

    @Test
    public void createTestWithExceptionWhenCheckRoom() {
        Mockito.doThrow(NotFoundException.class).when(roomService).checkExistsById(1);
        Mockito.doNothing().when(personService).checkExistsById(1);

        Assertions.assertThrows(NotFoundException.class, () -> reservationService.create(newReservation));
    }

    @Test
    public void createTestWithExceptionWhenReservationIsBusyCauseRoom() {
        Mockito.doNothing().when(personService).checkExistsById(1);
        Mockito.doNothing().when(roomService).checkExistsById(1);

        Mockito.when(reservationRepository.existsByRoomIdAndDate(1, start, end)).thenReturn(true);
        Mockito.when(reservationRepository.existsByPersonIdAndDate(1, start, end)).thenReturn(false);

        Assertions.assertThrows(NotFoundException.class, () -> reservationService.create(newReservation));
    }

    @Test
    public void createTestWithExceptionWhenReservationIsBusyCausePerson() {
        Mockito.doNothing().when(personService).checkExistsById(1);
        Mockito.doNothing().when(roomService).checkExistsById(1);

        Mockito.when(reservationRepository.existsByRoomIdAndDate(1, start, end)).thenReturn(false);
        Mockito.when(reservationRepository.existsByPersonIdAndDate(1, start, end)).thenReturn(true);

        Assertions.assertThrows(NotFoundException.class, () -> reservationService.create(newReservation));
    }

    @Test
    public void updateTest() {
        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.of(createdReservation));
        Mockito.when(reservationRepository.save(updatedReservation)).thenReturn(updatedReservation);

        Assertions.assertEquals(updatedReservation, reservationService.update(updatedReservation));
    }

    @Test
    public void updateTestWithException() {
        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> reservationService.update(updatedReservation));
    }

    @Test
    public void getAllTest() {
        List<Reservation> reservations = getList();

        Mockito.when(reservationRepository.findAll()).thenReturn(reservations);

        Assertions.assertEquals(reservations, reservationService.getAll());
    }

    @Test
    public void getCurrentTest() {
        List<Reservation> reservations = getList();

        Mockito.when(reservationRepository.findCurrentOperations(start)).thenReturn(reservations);

        Assertions.assertEquals(reservations, reservationService.getCurrent(start));
    }

    @Test
    public void getActualForPeriodTest() {
        List<Reservation> reservations = getList();

        Mockito.when(reservationRepository.findByPeriod(start, end)).thenReturn(reservations);

        Assertions.assertEquals(reservations, reservationService.getActualForPeriod(start, end));
    }

    @Test
    public void deleteByIdTest() {
        Mockito.when(reservationRepository.findById(1)).thenReturn(Optional.of(createdReservation));

        reservationService.deleteById(1);
        Mockito.verify(reservationRepository, Mockito.times(1)).deleteById(1);
    }
}
