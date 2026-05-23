package com.optifit.backend.mapper;

import com.optifit.backend.dto.response.*;
import com.optifit.backend.model.*;

import java.util.List;

public final class AppMapper {

    private AppMapper() {
    }

    public static UserProfileResponse toProfile(User user) {
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .age(user.getAge())
                .height(user.getHeight())
                .weight(user.getWeight())
                .goal(user.getGoal())
                .build();
    }

    public static ExerciseResponse toExercise(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .muscleGroup(exercise.getMuscleGroup())
                .description(exercise.getDescription())
                .caloriesPerSet(exercise.getCaloriesPerSet())
                .build();
    }

    public static WorkoutResponse toWorkout(Workout workout) {
        List<WorkoutExerciseResponse> items = workout.getExercises().stream()
                .map(AppMapper::toWorkoutExercise)
                .toList();

        return WorkoutResponse.builder()
                .id(workout.getId())
                .title(workout.getTitle())
                .workoutDate(workout.getWorkoutDate())
                .durationMinutes(workout.getDurationMinutes())
                .notes(workout.getNotes())
                .exercises(items)
                .build();
    }

    public static WorkoutExerciseResponse toWorkoutExercise(WorkoutExercise item) {
        return WorkoutExerciseResponse.builder()
                .id(item.getId())
                .exerciseId(item.getExercise().getId())
                .exerciseName(item.getExercise().getName())
                .muscleGroup(item.getExercise().getMuscleGroup())
                .sets(item.getSets())
                .reps(item.getReps())
                .weightKg(item.getWeightKg())
                .build();
    }

    public static NutritionPlanResponse toNutritionPlan(NutritionPlan plan) {
        List<MealResponse> meals = plan.getMeals().stream()
                .map(AppMapper::toMeal)
                .toList();

        int totalCalories = meals.stream()
                .map(MealResponse::getCalories)
                .filter(calories -> calories != null)
                .mapToInt(Integer::intValue)
                .sum();

        return NutritionPlanResponse.builder()
                .id(plan.getId())
                .planDate(plan.getPlanDate())
                .targetCalories(plan.getTargetCalories())
                .totalCalories(totalCalories)
                .meals(meals)
                .build();
    }

    public static MealResponse toMeal(Meal meal) {
        return MealResponse.builder()
                .id(meal.getId())
                .name(meal.getName())
                .calories(meal.getCalories())
                .protein(meal.getProtein())
                .carbs(meal.getCarbs())
                .fat(meal.getFat())
                .build();
    }
}
