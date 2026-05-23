package com.optifit.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WorkoutRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Workout date is required")
    private LocalDate workoutDate;

    private Integer durationMinutes;

    private String notes;

    @Valid
    private List<WorkoutExerciseItemRequest> exercises = new ArrayList<>();
}
