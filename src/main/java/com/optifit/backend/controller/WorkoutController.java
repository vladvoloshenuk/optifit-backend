package com.optifit.backend.controller;

import com.optifit.backend.dto.request.WorkoutRequest;
import com.optifit.backend.dto.response.ApiResponse;
import com.optifit.backend.dto.response.WorkoutResponse;
import com.optifit.backend.service.WorkoutService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class WorkoutController {

    private final WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<WorkoutResponse>>> getMyWorkouts() {
        return ResponseEntity.ok(ApiResponse.success(workoutService.getMyWorkouts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkoutResponse>> getMyWorkout(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(workoutService.getMyWorkout(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WorkoutResponse>> create(
            @Valid @RequestBody WorkoutRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Workout created", workoutService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WorkoutResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody WorkoutRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success("Workout updated", workoutService.update(id, request))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        workoutService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Workout deleted", null));
    }
}
