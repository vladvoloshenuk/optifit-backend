package com.optifit.backend.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequest {

    private String username;

    private Integer age;

    private Double height;

    private Double weight;

    private String goal;
}
