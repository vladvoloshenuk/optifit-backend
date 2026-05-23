package com.optifit.backend.dto.response;

import com.optifit.backend.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Integer age;
    private Double height;
    private Double weight;
    private String goal;
}
