package com.optifit.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealResponse {

    private Long id;
    private String name;
    private Integer calories;
    private Double protein;
    private Double carbs;
    private Double fat;
}
