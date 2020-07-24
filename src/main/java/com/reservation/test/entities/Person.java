package com.reservation.test.entities;

import com.reservation.test.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    Integer id;
    String name;
    Position position;
}
