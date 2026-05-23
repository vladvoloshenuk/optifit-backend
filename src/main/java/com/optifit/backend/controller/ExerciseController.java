package com.optifit.backend.controller;

import com.optifit.backend.dto.request.ExerciseRequest;
import com.optifit.backend.dto.response.ApiResponse;
import com.optifit.backend.dto.response.ExerciseResponse;
import com.optifit.backend.service.ExerciseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ExerciseResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(exerciseService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExerciseResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(exerciseService.getById(id)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ExerciseResponse>> create(
            @Valid @RequestBody ExerciseRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Exercise created", exerciseService.create(request)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ExerciseResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ExerciseRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("Exercise updated", exerciseService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        exerciseService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Exercise deleted", null));
    }
}
