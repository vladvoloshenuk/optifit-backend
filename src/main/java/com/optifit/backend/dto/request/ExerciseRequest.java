package com.optifit.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Muscle group is required")
    private String muscleGroup;

    private String description;

    private Integer caloriesPerSet;
}
