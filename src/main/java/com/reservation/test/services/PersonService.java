package com.reservation.test.services;

import com.reservation.test.dto.PersonDto;
import com.reservation.test.entities.Person;
import com.reservation.test.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.NotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void create(Person newPerson) {
        if (true) {
            personRepository.save(newPerson);
        }
    }

    public Person update(Person updatedPerson) {
        if (true) {
           return personRepository.save(updatedPerson);
        }
        else throw new NotFoundException();
    }

    public List<Person> getAll() {
       return personRepository.findAll();
    }

    public void removeById(Integer id) {
        if (true) {
            personRepository.deleteById(id);
        }
    }
}
