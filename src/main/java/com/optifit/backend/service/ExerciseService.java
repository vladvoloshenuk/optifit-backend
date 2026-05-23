package com.optifit.backend.service;

import com.optifit.backend.dto.request.ExerciseRequest;
import com.optifit.backend.dto.response.ExerciseResponse;
import com.optifit.backend.exception.ResourceNotFoundException;
import com.optifit.backend.mapper.AppMapper;
import com.optifit.backend.model.Exercise;
import com.optifit.backend.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Transactional(readOnly = true)
    public List<ExerciseResponse> getAll() {
        return exerciseRepository.findAll().stream()
                .map(AppMapper::toExercise)
                .toList();
    }

    @Transactional(readOnly = true)
    public ExerciseResponse getById(Long id) {
        return AppMapper.toExercise(findExercise(id));
    }

    @Transactional
    public ExerciseResponse create(ExerciseRequest request) {
        Exercise exercise = Exercise.builder()
                .name(request.getName())
                .muscleGroup(request.getMuscleGroup())
                .description(request.getDescription())
                .caloriesPerSet(request.getCaloriesPerSet())
                .build();

        return AppMapper.toExercise(exerciseRepository.save(exercise));
    }

    @Transactional
    public ExerciseResponse update(Long id, ExerciseRequest request) {
        Exercise exercise = findExercise(id);

        exercise.setName(request.getName());
        exercise.setMuscleGroup(request.getMuscleGroup());
        exercise.setDescription(request.getDescription());
        exercise.setCaloriesPerSet(request.getCaloriesPerSet());

        return AppMapper.toExercise(exerciseRepository.save(exercise));
    }

    @Transactional
    public void delete(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exercise not found");
        }
        exerciseRepository.deleteById(id);
    }

    public Exercise findExercise(Long id) {
        return exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));
    }
}
