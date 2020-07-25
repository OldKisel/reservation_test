package com.reservation.test.converters;

import com.reservation.test.dto.RoomDto;
import com.reservation.test.entities.Room;
import org.springframework.core.convert.converter.Converter;

public class RoomToRoomDtoConverter implements Converter<Room, RoomDto> {
    @Override
    public RoomDto convert(Room source) {
        return new RoomDto(source.getId(), source.getNumber(), source.getType());
    }
}
