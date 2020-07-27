package com.reservation.test.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer roomId;
    Integer personId;
    String name;
    String description;
    Instant startDate;
    Instant endDate;

    public Reservation(Integer roomId,
                       Integer personId,
                       String name,
                       String description,
                       Instant startDate,
                       Instant endDate) {
        this.roomId = roomId;
        this.personId = personId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
