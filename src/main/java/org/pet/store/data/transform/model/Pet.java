package org.pet.store.data.transform.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

import org.pet.store.data.transform.model.enums.GenderType;
import org.springframework.validation.annotation.Validated;

@Validated
public class Pet {

    private String name;

    private GenderType gender;

    private List<VaccinationRecord> vaccinationRecordList;

    private String label;

    private String id;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Pattern(regexp = "[a-f0-9]{8}(?:-[a-f0-9]{4}){4}[a-f0-9]{8}")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<VaccinationRecord> getVaccinationRecordList() {
        return vaccinationRecordList;
    }

    public void setVaccinationRecordList(List<VaccinationRecord> vaccinationRecordList) {
        this.vaccinationRecordList = vaccinationRecordList;
    }

    @NotNull
    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }
}
