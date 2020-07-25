package com.reservation.test.dto;

import com.reservation.test.entities.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    Integer id;
    @NotNull
    @NotBlank
    Integer number;
    @NotNull
    RoomType type;
}

