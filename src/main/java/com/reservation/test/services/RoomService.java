package com.reservation.test.services;

import com.reservation.test.entities.Room;
import com.reservation.test.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void create(Room room) {

        Integer number = room.getNumber();
        if (roomRepository.findByNumber(number).isPresent()) {
            throw new NotFoundException(String.format("Room with number %s already exists", number));
        } else {
            roomRepository.save(room);
        }
    }

    public Room update(Room updatedRoom) {
        checkExistsById(updatedRoom.getId());

        return roomRepository.save(updatedRoom);
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public void deleteById(Integer id) {
        checkExistsById(id);

        roomRepository.deleteById(id);
    }

    public void checkExistsById(Integer id) {
        if (roomRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Room with id %s is not exists", id));
        }
    }
}
