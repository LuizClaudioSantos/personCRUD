package me.luizclaudiosantos.personCRUD.repositories;

import me.luizclaudiosantos.personCRUD.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    Person save(Person person);

    Iterable<Person> findAll();

    long count();

    void delete(Person entity);

    long deleteByid(long id);

}
