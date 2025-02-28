package org.pet.store.data.transform.model;

import jakarta.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class Inference {

    private String label;

    private Float score;

    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @NotNull
    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
