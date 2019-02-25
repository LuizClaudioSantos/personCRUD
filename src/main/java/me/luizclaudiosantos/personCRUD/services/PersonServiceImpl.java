package me.luizclaudiosantos.personCRUD.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import me.luizclaudiosantos.personCRUD.models.Person;
import me.luizclaudiosantos.personCRUD.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Optional<Person> add(Person person) {

        if (person.getId() == null) {
            Person personSaved = this.personRepository.save(person);
            return Optional.of(personSaved);
        } 

        return  Optional.empty();
    }

    @Override
    public Optional<Person> edit(Person person) {

        if (person.getId() != null) {
            Person personSaved = this.personRepository.save(person);
            return Optional.of(personSaved);
        } 

        return  Optional.empty();
    }

    @Override
    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    @Override
    public long count() {
        return this.personRepository.count();
    }

    @Override
    public Iterable<Person> list() {
        return this.personRepository.findAll();
    }

}