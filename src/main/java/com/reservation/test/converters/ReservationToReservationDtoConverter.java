package com.reservation.test.converters;

import com.reservation.test.dto.ReservationDto;
import com.reservation.test.entities.Reservation;
import org.springframework.core.convert.converter.Converter;

public class ReservationToReservationDtoConverter implements Converter<Reservation, ReservationDto> {
    @Override
    public ReservationDto convert(Reservation source) {
        return new ReservationDto(source.getId(),
                source.getPersonId(),
                source.getRoomId(),
                source.getName(),
                source.getDescription(),
                source.getStartDate(),
                source.getEndDate());
    }
}
