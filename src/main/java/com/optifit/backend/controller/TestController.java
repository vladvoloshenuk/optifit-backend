package com.optifit.backend.controller;

import com.optifit.backend.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/test")
    public ApiResponse<String> test() {
        return ApiResponse.success("Protected endpoint works");
    }
}
