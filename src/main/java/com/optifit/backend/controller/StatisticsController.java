package com.optifit.backend.controller;

import com.optifit.backend.dto.response.ApiResponse;
import com.optifit.backend.dto.response.StatisticsResponse;
import com.optifit.backend.service.StatisticsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<StatisticsResponse>> getSummary() {
        return ResponseEntity.ok(ApiResponse.success(statisticsService.getSummary()));
    }
}
