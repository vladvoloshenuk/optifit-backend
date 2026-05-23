package com.optifit.backend.controller;

import com.optifit.backend.dto.request.NutritionPlanRequest;
import com.optifit.backend.dto.response.ApiResponse;
import com.optifit.backend.dto.response.NutritionPlanResponse;
import com.optifit.backend.service.NutritionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nutrition/plans")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class NutritionController {

    private final NutritionService nutritionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<NutritionPlanResponse>>> getMyPlans() {
        return ResponseEntity.ok(ApiResponse.success(nutritionService.getMyPlans()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<NutritionPlanResponse>> getMyPlan(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(nutritionService.getMyPlan(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<NutritionPlanResponse>> create(
            @Valid @RequestBody NutritionPlanRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Nutrition plan created", nutritionService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<NutritionPlanResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody NutritionPlanRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("Nutrition plan updated", nutritionService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        nutritionService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Nutrition plan deleted", null));
    }
}
