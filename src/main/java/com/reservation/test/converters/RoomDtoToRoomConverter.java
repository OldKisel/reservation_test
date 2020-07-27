package com.reservation.test.converters;

import com.reservation.test.dto.RoomDto;
import com.reservation.test.entities.Room;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoomDtoToRoomConverter implements Converter<RoomDto, Room> {
    @Override
    public Room convert(RoomDto source) {
        return new Room(source.getNumber(), source.getType());
    }
}
