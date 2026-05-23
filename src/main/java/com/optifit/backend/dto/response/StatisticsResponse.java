package com.optifit.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsResponse {

    private long totalWorkouts;
    private long totalNutritionPlans;
    private int totalWorkoutMinutes;
    private int totalMealCalories;
    private Double currentWeight;
    private String goal;
}
