package com.optifit.backend.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NutritionPlanRequest {

    @NotNull(message = "Plan date is required")
    private LocalDate planDate;

    private Integer targetCalories;

    @Valid
    private List<MealRequest> meals = new ArrayList<>();
}
