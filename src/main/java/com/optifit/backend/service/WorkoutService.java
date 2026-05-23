package com.optifit.backend.service;

import com.optifit.backend.dto.request.WorkoutExerciseItemRequest;
import com.optifit.backend.dto.request.WorkoutRequest;
import com.optifit.backend.dto.response.WorkoutResponse;
import com.optifit.backend.exception.ResourceNotFoundException;
import com.optifit.backend.mapper.AppMapper;
import com.optifit.backend.model.User;
import com.optifit.backend.model.Workout;
import com.optifit.backend.model.WorkoutExercise;
import com.optifit.backend.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final ExerciseService exerciseService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<WorkoutResponse> getMyWorkouts() {
        User user = userService.getCurrentUser();
        return workoutRepository.findDetailedByUserId(user.getId()).stream()
                .map(AppMapper::toWorkout)
                .toList();
    }

    @Transactional(readOnly = true)
    public WorkoutResponse getMyWorkout(Long id) {
        User user = userService.getCurrentUser();
        Workout workout = workoutRepository.findDetailedByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        return AppMapper.toWorkout(workout);
    }

    @Transactional
    public WorkoutResponse create(WorkoutRequest request) {
        User user = userService.getCurrentUser();

        Workout workout = Workout.builder()
                .user(user)
                .title(request.getTitle())
                .workoutDate(request.getWorkoutDate())
                .durationMinutes(request.getDurationMinutes())
                .notes(request.getNotes())
                .exercises(new ArrayList<>())
                .build();

        addExercises(workout, request.getExercises());
        Workout saved = workoutRepository.save(workout);
        return AppMapper.toWorkout(saved);
    }

    @Transactional
    public WorkoutResponse update(Long id, WorkoutRequest request) {
        User user = userService.getCurrentUser();
        Workout workout = workoutRepository.findDetailedByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));

        workout.setTitle(request.getTitle());
        workout.setWorkoutDate(request.getWorkoutDate());
        workout.setDurationMinutes(request.getDurationMinutes());
        workout.setNotes(request.getNotes());

        workout.getExercises().clear();
        addExercises(workout, request.getExercises());

        return AppMapper.toWorkout(workoutRepository.save(workout));
    }

    @Transactional
    public void delete(Long id) {
        User user = userService.getCurrentUser();
        Workout workout = workoutRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Workout not found"));
        workoutRepository.delete(workout);
    }

    private void addExercises(Workout workout, List<WorkoutExerciseItemRequest> items) {
        if (items == null) {
            return;
        }

        for (WorkoutExerciseItemRequest item : items) {
            WorkoutExercise workoutExercise = WorkoutExercise.builder()
                    .workout(workout)
                    .exercise(exerciseService.findExercise(item.getExerciseId()))
                    .sets(item.getSets())
                    .reps(item.getReps())
                    .weightKg(item.getWeightKg())
                    .build();
            workout.getExercises().add(workoutExercise);
        }
    }
}
