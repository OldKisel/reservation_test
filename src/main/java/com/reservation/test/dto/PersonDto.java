package com.reservation.test.dto;

import com.reservation.test.entities.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    Integer id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 50)
    String name;
    @NotNull
    Position position;
}
