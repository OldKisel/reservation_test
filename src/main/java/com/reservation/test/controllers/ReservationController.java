package com.reservation.test.controllers;

import com.reservation.test.dto.ReservationDto;
import com.reservation.test.entities.Reservation;
import com.reservation.test.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ConversionService conversionService;

    @Autowired
    public ReservationController(ReservationService reservationService, ConversionService conversionService) {
        this.reservationService = reservationService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public void post(@RequestBody @Valid ReservationDto reservationDto) {
        Reservation newReservation = conversionService.convert(reservationDto, Reservation.class);
        reservationService.create(newReservation);
    }

    @PutMapping("/{id}")
    public ReservationDto put(@RequestBody @Valid ReservationDto reservationDto, @PathVariable Integer id) {
        Reservation updatedReservation = conversionService.convert(reservationDto, Reservation.class);
        updatedReservation.setId(id);

        return conversionService.convert(reservationService.update(updatedReservation), ReservationDto.class);
    }

    @GetMapping
    public List<ReservationDto> get() {
        return reservationService.getAll().stream().map(reservation ->
                conversionService.convert(reservation, ReservationDto.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        reservationService.deleteById(id);
    }
}
