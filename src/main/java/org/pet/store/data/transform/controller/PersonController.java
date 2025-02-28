package org.pet.store.data.transform.controller;

import static org.instancio.Select.field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.instancio.Instancio;
import org.pet.store.data.transform.model.Person;
import org.pet.store.data.transform.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet/store/")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private List<Person> people;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Constructor.
     */
    public PersonController() {
        logger.info("Initializing...");

        this.people = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            var person = Instancio
                .of(Person.class)
                .withMaxDepth(1)
                .set(field(Person::getPetList), new ArrayList<>())
                .set(field(Person::getId), UUID.randomUUID().toString())
                .create();
            this.people.add(person);
        }

        logger.info("...initialized.");
    }

    /**
     * Get a random person from this controller.
     *
     * @return A random cached person.
     * @throws JsonProcessingException Exception trying to output the person.
     */
    @GetMapping("/get-person")
    public Person getRandomPerson() throws JsonProcessingException {

        logger.info("Received a request to get a random person...");
        var random = new Random();
        var randomPerson = this.people.get(random.nextInt(this.people.size()));
        logger.info("Got person: {}", this.objectMapper.writeValueAsString(randomPerson));
        return randomPerson;
    }

    /**
     * Adopt a pet with a new owner.
     *
     * @param pet The pet to adopt.
     * @return The person object who adopted the pet.
     * @throws JsonProcessingException Exceptions from parsing out the random person.
     */
    @PostMapping("/adopt-pet")
    public Person adoptPet(@RequestBody @Valid final Pet pet)
        throws JsonProcessingException {
        logger.info("Received a pet to adopt: {}", pet);

        var random = new Random();
        var randomPerson = this.people.get(random.nextInt(this.people.size()));
        randomPerson.getPetList().add(pet);
        logger.info("Pet adopted by: {}", this.objectMapper.writeValueAsString(randomPerson));
        return randomPerson;
    }
}
