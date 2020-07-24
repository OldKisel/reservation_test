package com.reservation.test.entities;

import com.reservation.test.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    Integer id;
    Integer number;
    RoomType type;
}
