package com.optifit.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkoutResponse {

    private Long id;
    private String title;
    private LocalDate workoutDate;
    private Integer durationMinutes;
    private String notes;
    private List<WorkoutExerciseResponse> exercises;
}
