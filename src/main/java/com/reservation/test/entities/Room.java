package com.reservation.test.entities;

import com.reservation.test.entities.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer number;
    @Enumerated(value = EnumType.STRING)
    RoomType type;

    public Room(Integer number, RoomType type) {
        this.number = number;
        this.type = type;
    }
}
