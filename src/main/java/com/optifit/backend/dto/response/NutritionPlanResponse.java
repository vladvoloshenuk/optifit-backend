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
public class NutritionPlanResponse {

    private Long id;
    private LocalDate planDate;
    private Integer targetCalories;
    private Integer totalCalories;
    private List<MealResponse> meals;
}
