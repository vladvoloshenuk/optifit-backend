package com.optifit.backend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutExerciseItemRequest {

    @NotNull(message = "Exercise id is required")
    private Long exerciseId;

    private Integer sets;

    private Integer reps;

    private Double weightKg;
}
