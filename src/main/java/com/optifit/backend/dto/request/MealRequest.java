package com.optifit.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealRequest {

    @NotBlank(message = "Meal name is required")
    private String name;

    private Integer calories;

    private Double protein;

    private Double carbs;

    private Double fat;
}
