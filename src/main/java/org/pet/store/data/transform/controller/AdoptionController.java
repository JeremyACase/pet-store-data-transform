package org.pet.store.data.transform.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.pet.store.data.transform.model.AdoptionRecord;
import org.pet.store.data.transform.model.Person;
import org.pet.store.data.transform.model.Pet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet/store/")
public class AdoptionController {

    private static final Logger logger = LoggerFactory.getLogger(AdoptionController.class);

    /**
     * Provided a list of objects containing a pet and person, have the person adopt
     * the pet.
     *
     * @param mergedPayloads The merged payloads coming from upstream.
     * @return A person who now has the newly-adopted pet as a child object.
     */
    @PostMapping("/adopt-pet")
    public AdoptionRecord adoptPet(@RequestBody List<Object> mergedPayloads) {

        var personRecord = mergedPayloads
            .stream()
            .filter(Person.class::isInstance)
            .findFirst();

        if (personRecord.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Cannot adopt a pet without a person!");
        }

        var petRecord = mergedPayloads
            .stream()
            .filter(Pet.class::isInstance)
            .findFirst();

        if (petRecord.isEmpty()) {
            throw new IllegalArgumentException("ERROR: No pet is present to adopt!");
        }

        var pet = (Pet) petRecord.get();
        var person = (Person) personRecord.get();
        person.getPetList().add(pet);

        var adoptionRecord = new AdoptionRecord();
        adoptionRecord.setPet(pet);
        adoptionRecord.setPerson(person);
        adoptionRecord.setTimestamp(OffsetDateTime.now());

        return adoptionRecord;
    }
}
