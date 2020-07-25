package com.reservation.test.converters;

import com.reservation.test.dto.PersonDto;
import com.reservation.test.entities.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonDtoToPersonConverter implements Converter<PersonDto, Person> {
    @Override
    public Person convert(PersonDto source) {
        return new Person(source.getName(), source.getPosition());
    }
}
