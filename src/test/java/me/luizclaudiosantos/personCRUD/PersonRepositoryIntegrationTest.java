package me.luizclaudiosantos.personCRUD;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import me.luizclaudiosantos.personCRUD.models.Person;
import me.luizclaudiosantos.personCRUD.repositories.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PersonRepositoryIntegrationTest {

    @Autowired
    private  TestEntityManager entityManager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSaveAndCount() {
        
        Person person1 = new Person("Person 1 first name ", "Person 1 last name");
        personRepository.save(person1);

        Person person2 = new Person("Person 2 first name ", "Person 2 last name");
        personRepository.save(person2);

        assertTrue(personRepository.count() == 2 );

    }

    @Test
    public void testSave() {
        
        Person person1 = new Person("Person 1 first name ", "Person 1 last name");
        Person personSaved = personRepository.save(person1);


        assertTrue(personSaved.getId() != null );

    }

    @Test
    public void testGetId() {
        
        Person person1 = new Person("Person 1 first name ", "Person 1 last name");

        Person personSaved = entityManager.persist(person1);
      
        assertEquals(personSaved, personRepository.findById(personSaved.getId()).get() );

    }

}


