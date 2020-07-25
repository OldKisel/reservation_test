package com.reservation.test.services;

import com.reservation.test.entities.Room;
import com.reservation.test.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void create(Room room) {
        if (true) {
            roomRepository.save(room);
        }
    }

    public Room update(Room updatedRoom) {
        if (true) {
            return roomRepository.save(updatedRoom);
        } else throw new BadRequestException();
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public void deleteById(Integer id) {
        roomRepository.deleteById(id);
    }
}
