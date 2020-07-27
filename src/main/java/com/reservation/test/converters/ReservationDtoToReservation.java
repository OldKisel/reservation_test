package com.reservation.test.converters;

import com.reservation.test.dto.ReservationDto;
import com.reservation.test.entities.Reservation;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReservationDtoToReservation implements Converter<ReservationDto, Reservation> {
    @Override
    public Reservation convert(ReservationDto source) {
        return new Reservation(source.getRoomId(),
                source.getPersonId(),
                source.getName(),
                source.getDescription(),
                source.getStartDate(),
                source.getEndDate());
    }
}
