package com.reservation.test.controllers;

import com.reservation.test.dto.PersonDto;
import com.reservation.test.entities.Person;
import com.reservation.test.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/persons")
public class PersonController {
    private final PersonService personService;
    private final ConversionService conversionService;

    @Autowired
    public PersonController(PersonService personService, ConversionService conversionService) {
        this.personService = personService;
        this.conversionService = conversionService;
    }

    @PostMapping
    public void post(@RequestBody @Valid PersonDto personDto) {
        Person newPerson = conversionService.convert(personDto, Person.class);
        personService.create(newPerson);
    }

    @PutMapping("/{id}")
    public PersonDto put(@RequestBody @Valid PersonDto personDto, @PathVariable Integer id) {
        Person updatedPerson = conversionService.convert(personDto, Person.class);
        updatedPerson.setId(id);
        return conversionService.convert(personService.update(updatedPerson), PersonDto.class);
    }

    @GetMapping
    public List<PersonDto> get() {
        return personService.getAll().stream().map(person ->
                conversionService.convert(person, PersonDto.class)).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        personService.removeById(id);
    }
}
