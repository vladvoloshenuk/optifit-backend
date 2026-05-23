package com.optifit.backend.service;

import com.optifit.backend.dto.response.StatisticsResponse;
import com.optifit.backend.model.NutritionPlan;
import com.optifit.backend.model.User;
import com.optifit.backend.model.Workout;
import com.optifit.backend.repository.NutritionPlanRepository;
import com.optifit.backend.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final WorkoutRepository workoutRepository;
    private final NutritionPlanRepository nutritionPlanRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public StatisticsResponse getSummary() {
        User user = userService.getCurrentUser();

        List<Workout> workouts = workoutRepository.findDetailedByUserId(user.getId());
        List<NutritionPlan> plans = nutritionPlanRepository.findDetailedByUserId(user.getId());

        int totalMinutes = workouts.stream()
                .map(Workout::getDurationMinutes)
                .filter(minutes -> minutes != null)
                .mapToInt(Integer::intValue)
                .sum();

        int totalMealCalories = plans.stream()
                .flatMap(plan -> plan.getMeals().stream())
                .map(meal -> meal.getCalories())
                .filter(calories -> calories != null)
                .mapToInt(Integer::intValue)
                .sum();

        return StatisticsResponse.builder()
                .totalWorkouts(workouts.size())
                .totalNutritionPlans(plans.size())
                .totalWorkoutMinutes(totalMinutes)
                .totalMealCalories(totalMealCalories)
                .currentWeight(user.getWeight())
                .goal(user.getGoal())
                .build();
    }
}
