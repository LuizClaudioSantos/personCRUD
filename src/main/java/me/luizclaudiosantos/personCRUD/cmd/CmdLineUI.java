package me.luizclaudiosantos.personCRUD.cmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import me.luizclaudiosantos.personCRUD.PersonCrudApplication;
import me.luizclaudiosantos.personCRUD.models.Person;
import me.luizclaudiosantos.personCRUD.services.PersonService;
import me.luizclaudiosantos.personCRUD.services.PersonServiceImpl;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class CmdLineUI implements ApplicationRunner {

    private PersonService personService;

    private ObjectMapper objectMapper;

    private XmlMapper xmlMapper;

    private final Logger logger = LoggerFactory.getLogger(PersonCrudApplication.class);

    public CmdLineUI(PersonService personService, ObjectMapper objectMapper, XmlMapper xmlMapper) {
        this.personService = personService;
        this.xmlMapper = xmlMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (args.containsOption("add")) {

            String json = concatInputString(args);

            Person p = jsonToPerson(json);

            Optional<Person> optional = personService.add(p);

            if (optional.isPresent()) {
                logger.info("Person added successfully:  " + optional.get());
            } else {
                logger.warn("It was not possible to add the person:  " + p);
            }

        } else if (args.containsOption("count")) {

            logger.info("Total of persons: " + personService.count());

        } else if (args.containsOption("list")) {

            Iterable<Person> it = personService.list();
            for (Person person : it) {
                logger.info("Person: " + person);
            }

        } else if (args.containsOption("delete")) {

            String json = concatInputString(args);

            Person p = jsonToPerson(json);

            personService.delete(p);

        } else if (args.containsOption("edit")) {

            String json = concatInputString(args);

            Person p = jsonToPerson(json);

            Optional<Person> optional = personService.edit(p);

            if (optional.isPresent()) {
                logger.info("Person added successfully:  " + optional.get());
            } else {
                logger.warn("It was not possible to add the person:  " + p);
            }

        } else if (args.containsOption("addFile")) {

            System.out.println(System.getProperty("user.dir"));

            Optional<String> xml = xmlFileContent(concatInputString(args));

            if (xml.isPresent()) {
            
                Optional <Person> personOptional =  personService
                    .add( xmlMapper.readValue(xml.get(), Person.class));

                personOptional
                    .ifPresent( p  -> logger.info("User added sucessfully: " + p));
                        
            }


        } else {
            printHelp();
        }

    }

    private Optional<String>  xmlFileContent(String file)  {

        Path path = FileSystems.getDefault().getPath(file);

        Stream<String> lines;
        String xml = "";
        try {
            lines = Files.lines(path);
            xml = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(xml);

    }

    private Person jsonToPerson(String json) throws IOException {
        return objectMapper.readValue(json, Person.class);

    }

    private String concatInputString(ApplicationArguments args){
        List<String> list = args.getNonOptionArgs();
        String values = "";
        for (String value : list) {
            values += value;
        }
        return values;
    }

    private void printHelp() {
        System.out.println("/*****************************************************/");
        System.out.println("To add a Person use the command");
        System.out.println("--add {\"firstName\" : \"first Name\", \"lastName\": \"Last Name\"}");

        System.out.println("/* To edit a Person use the command*/");
        System.out.println("--edit {\"id\": 1,  \"firstName\" : \"first Name\", \"lastName\": \"Last Name\"}");

        System.out.println("/* To delete a Person use the id*/");
        System.out.println("--delete {\"id\": 1,  \"firstName\" : \"first Name\", \"lastName\": \"Last Name\"}");

        System.out.println("/* To count the total of persons use the command*/");
        System.out.println("--count");

        System.out.println("/* To edit a Person use the command*/");
        System.out.println("--list");
    }

}
