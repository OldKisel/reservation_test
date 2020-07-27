package com.reservation.test.services;

import com.reservation.test.entities.Person;
import com.reservation.test.entities.enums.Position;
import com.reservation.test.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonServiceTests {
    private PersonService personService;
    private PersonRepository personRepository;

    @BeforeEach
    public void init() {
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);
    }

    Person newPerson = new Person(null, "Ivan", Position.THERAPIST);
    Person createdPerson = new Person(1, "Ivan", Position.THERAPIST);
    Person updatedPerson = new Person(1, "Egor", Position.THERAPIST);


    @Test
    public void createTestDone() {
        Mockito.when(personRepository.findByName("Ivan")).thenReturn(Optional.empty());

        personService.create(newPerson);
        Mockito.verify(personRepository, Mockito.times(1)).save(Mockito.eq(newPerson));
    }

    @Test
    public void createTestWithException() {

        Mockito.when(personRepository.findByName("Ivan")).thenReturn(Optional.of(createdPerson));

        Assertions.assertThrows(NotFoundException.class, () -> personService.create(newPerson));
    }

    @Test
    public void updateTest() {
        Person createdPerson = new Person(1, "Ivan", Position.THERAPIST);

        Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(createdPerson));
        Mockito.when(personRepository.save(updatedPerson)).thenReturn(updatedPerson);

        Person actual = personService.update(updatedPerson);

        Assertions.assertEquals(updatedPerson, actual);
    }

    @Test
    public void updateTestWithException() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> personService.update(updatedPerson));
    }

    @Test
    public void getTest() {
        List<Person> persons = new ArrayList<>();
        Person createdPerson = new Person(1, "Ivan", Position.THERAPIST);
        persons.add(createdPerson);

        Mockito.when(personRepository.findAll()).thenReturn(persons);

        Assertions.assertEquals(persons, personService.getAll());
    }

    @Test
    public void deleteTest() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(createdPerson));

        personService.removeById(1);

        Mockito.verify(personRepository, Mockito.times(1)).deleteById(Mockito.eq(1));
    }

    @Test
    public void deleteTestWithException() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> personService.removeById(1));
    }

    @Test
    public void checkTest() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(createdPerson));

        personService.checkExistsById(1);

        Mockito.verify(personRepository, Mockito.times(1)).findById(1);

    }

    @Test
    public void checkTestWithException() {
        Mockito.when(personRepository.findById(1)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> personService.checkExistsById(1));
    }
}
