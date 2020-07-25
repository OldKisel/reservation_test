package com.reservation.test.controllers;

import com.reservation.test.dto.RoomDto;
import com.reservation.test.entities.Room;
import com.reservation.test.services.RoomService;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private final ConversionService conversionService;

    public RoomController(RoomService roomService, ConversionService conversionService) {
        this.roomService = roomService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public void post(@Valid @RequestBody RoomDto roomDto) {
        Room newRoom = conversionService.convert(roomDto, Room.class);
        roomService.create(newRoom);
    }

    @PutMapping("/{id}")
    public RoomDto put(@Valid @RequestBody RoomDto roomDto, @PathVariable Integer id) {
        Room updatedRoom = conversionService.convert(roomDto, Room.class);
        updatedRoom.setId(id);
        return conversionService.convert(roomService.update(updatedRoom), RoomDto.class);
    }

    @GetMapping
    public List<RoomDto> get() {
        return roomService.getAll().stream().map(room ->
                conversionService.convert(room, RoomDto.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        roomService.deleteById(id);
    }
}
