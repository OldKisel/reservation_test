package com.reservation.test.services;

import com.reservation.test.entities.Room;
import com.reservation.test.entities.enums.RoomType;
import com.reservation.test.repositories.RoomRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomServiceTests {

    private RoomService roomService;
    private RoomRepository roomRepository;

    @BeforeEach
    public void init() {
        roomRepository = Mockito.mock(RoomRepository.class);
        roomService = new RoomService(roomRepository);
    }

    @Test
    public void createTestDone() {
        Room newRoom = new Room(null, 101, RoomType.LABORATORY);
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);

        Mockito.when(roomRepository.findByNumber(101)).thenReturn(Optional.empty());

        roomService.create(newRoom);
        Mockito.verify(roomRepository, Mockito.times(1)).save(Mockito.eq(newRoom));
    }

    @Test
    public void createTestWithException() {
        Room newRoom = new Room(null, 101, RoomType.LABORATORY);
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);

        Mockito.when(roomRepository.findByNumber(101)).thenReturn(Optional.of(createdRoom));

        Assertions.assertThrows(NotFoundException.class, () -> roomService.create(newRoom));
    }

    @Test
    public void updateTest() {
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);
        Room updatedRoom = new Room(1, 102, RoomType.LABORATORY);

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.of(createdRoom));
        Mockito.when(roomRepository.save(updatedRoom)).thenReturn(updatedRoom);

        Room actual = roomService.update(updatedRoom);

        Assertions.assertEquals(updatedRoom, actual);
    }

    @Test
    public void updateTestWithException() {
        Room updatedRoom = new Room(1, 102, RoomType.LABORATORY);

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> roomService.update(updatedRoom));
    }

    @Test
    public void getTest() {
        List<Room> rooms = new ArrayList<>();
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);
        rooms.add(createdRoom);

        Mockito.when(roomRepository.findAll()).thenReturn(rooms);

        Assertions.assertEquals(rooms, roomService.getAll());
    }

    @Test
    public void deleteTest() {
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.of(createdRoom));

        roomService.deleteById(1);

        Mockito.verify(roomRepository, Mockito.times(1)).deleteById(Mockito.eq(1));
    }

    @Test
    public void deleteTestWithException() {

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> roomService.deleteById(1));
    }

    @Test
    public void checkTest() {
        Room createdRoom = new Room(1, 101, RoomType.LABORATORY);

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.of(createdRoom));

        roomService.checkExistsById(1);

        Mockito.verify(roomRepository, Mockito.times(1)).findById(1);

    }

    @Test
    public void checkTestWithException() {
        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> roomService.checkExistsById(1));
    }
}
