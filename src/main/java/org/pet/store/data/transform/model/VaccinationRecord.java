package org.pet.store.data.transform.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

import org.pet.store.data.transform.model.enums.VaccineType;
import org.springframework.validation.annotation.Validated;

@Validated
public class VaccinationRecord {

    private VaccineType vaccineType;

    private OffsetDateTime timestamp;

    @NotNull
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    @NotNull
    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
