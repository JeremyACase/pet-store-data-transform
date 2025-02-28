package org.pet.store.data.transform.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import org.springframework.validation.annotation.Validated;

@Validated
public class AdoptionRecord {

    private Person person;

    private Pet pet;

    private OffsetDateTime timestamp;

    @NotNull
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @NotNull
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @NotNull
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
