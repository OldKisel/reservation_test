package com.reservation.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    Integer id;
    Integer personId;
    Integer roomId;
    @NotNull
    @NotBlank
    String name;
    String description;
    Instant startDate;
    Instant endDate;
}
