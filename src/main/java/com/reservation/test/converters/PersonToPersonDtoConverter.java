package com.reservation.test.converters;

import com.reservation.test.dto.PersonDto;
import com.reservation.test.entities.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonDtoConverter implements Converter<Person, PersonDto> {
    @Override
    public PersonDto convert(Person source) {
        return new PersonDto(source.getId(), source.getName(), source.getPosition());
    }
}
