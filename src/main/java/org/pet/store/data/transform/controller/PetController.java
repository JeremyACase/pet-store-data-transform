package org.pet.store.data.transform.controller;

import static org.instancio.Select.field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.instancio.Instancio;
import org.pet.store.data.transform.model.Inference;
import org.pet.store.data.transform.model.Pet;
import org.pet.store.data.transform.model.VaccinationRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pet/store/")
public class PetController {

    private static final Logger logger = LoggerFactory.getLogger(PetController.class);
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * An endpoint that can create a new pet provided an inference score.
     *
     * @param inferenceList A list of inferences from a classification model provided an image.
     * @return A new pet.
     * @throws JsonProcessingException Exception from parsing pet.
     */
    @PostMapping("/create-pet")
    public Pet createPet(@RequestBody @Valid final List<Inference> inferenceList)
        throws JsonProcessingException {
        logger.info("Received a list of inferences to create a new pet from: {}",
            inferenceList);
        inferenceList.sort(Comparator.comparing(Inference::getScore));

        var pet = Instancio
            .of(Pet.class)
            .withMaxDepth(1)
            .set(field(Pet::getId), UUID.randomUUID().toString())
            .set(field(Pet::getLabel), inferenceList.get(0).getLabel())
            .set(field(Pet::getVaccinationRecordList), new ArrayList<>())
            .create();

        logger.info("Generated pet: {}", this.objectMapper.writeValueAsString(pet));

        return pet;
    }

    /**
     * Generate a vaccination record for a provided pet Object.
     *
     * @param pet The pet to generate a vaccination record for.
     * @return A pet.
     * @throws JsonProcessingException Exceptions from JSON processing.
     */
    @PostMapping("/vaccinate-pet")
    public Pet vaccinatePet(@RequestBody @Valid final Pet pet) throws JsonProcessingException {
        logger.info("Received a pet to vaccinate: {}", pet);

        var vaccinationRecord = Instancio
            .of(VaccinationRecord.class)
            .withMaxDepth(1)
            .set(field(VaccinationRecord::getTimestamp), OffsetDateTime.now())
            .create();

        logger.info("Generated vaccination: {}",
            this.objectMapper.writeValueAsString(vaccinationRecord));

        pet.getVaccinationRecordList().add(vaccinationRecord);
        return pet;
    }
}
