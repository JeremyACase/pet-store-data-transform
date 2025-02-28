package org.pet.store.data.transform.model.enums;

public enum VaccineType {

    RABIES("RABIES"),
    FLU("FLU"),
    MEASLES("MEASLES");

    private String value;

    VaccineType(String value) {
        this.value = value;
    }

}
