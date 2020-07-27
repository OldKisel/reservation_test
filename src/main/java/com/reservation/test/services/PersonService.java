package com.reservation.test.services;

import com.reservation.test.entities.Person;
import com.reservation.test.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void create(Person newPerson) {
        String name = newPerson.getName();

        if (personRepository.findByName(name).isPresent()) {
            throw new NotFoundException(String.format("Person with name %s is already exists", name));
        } else {
            personRepository.save(newPerson);
        }
    }

    public Person update(Person updatedPerson) {
        Integer id = updatedPerson.getId();

        checkExistsById(id);

        return personRepository.save(updatedPerson);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }


    public void removeById(Integer id) {

        checkExistsById(id);

        personRepository.deleteById(id);
    }

    public void checkExistsById(Integer id) {
        if (personRepository.findById(id).isEmpty()) {
            throw new NotFoundException(String.format("Cannot find person with id %s", id));
        }
    }
}
