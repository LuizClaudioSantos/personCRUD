package me.luizclaudiosantos.personCRUD.services;

import java.util.Optional;

import me.luizclaudiosantos.personCRUD.models.Person;

public interface  PersonService {

    public Optional<Person> add(Person person);
    
    public Optional<Person> edit(Person person);

    public void delete(Person person);

    public long count();

    public Iterable<Person> list();

}