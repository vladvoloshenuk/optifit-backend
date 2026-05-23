package com.optifit.backend.service;

import com.optifit.backend.dto.request.MealRequest;
import com.optifit.backend.dto.request.NutritionPlanRequest;
import com.optifit.backend.dto.response.NutritionPlanResponse;
import com.optifit.backend.exception.ResourceNotFoundException;
import com.optifit.backend.mapper.AppMapper;
import com.optifit.backend.model.Meal;
import com.optifit.backend.model.NutritionPlan;
import com.optifit.backend.model.User;
import com.optifit.backend.repository.NutritionPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final NutritionPlanRepository nutritionPlanRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public List<NutritionPlanResponse> getMyPlans() {
        User user = userService.getCurrentUser();
        return nutritionPlanRepository.findDetailedByUserId(user.getId()).stream()
                .map(AppMapper::toNutritionPlan)
                .toList();
    }

    @Transactional(readOnly = true)
    public NutritionPlanResponse getMyPlan(Long id) {
        User user = userService.getCurrentUser();
        NutritionPlan plan = nutritionPlanRepository.findDetailedByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutrition plan not found"));
        return AppMapper.toNutritionPlan(plan);
    }

    @Transactional
    public NutritionPlanResponse create(NutritionPlanRequest request) {
        User user = userService.getCurrentUser();

        NutritionPlan plan = NutritionPlan.builder()
                .user(user)
                .planDate(request.getPlanDate())
                .targetCalories(request.getTargetCalories())
                .meals(new ArrayList<>())
                .build();

        addMeals(plan, request.getMeals());
        return AppMapper.toNutritionPlan(nutritionPlanRepository.save(plan));
    }

    @Transactional
    public NutritionPlanResponse update(Long id, NutritionPlanRequest request) {
        User user = userService.getCurrentUser();
        NutritionPlan plan = nutritionPlanRepository.findDetailedByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutrition plan not found"));

        plan.setPlanDate(request.getPlanDate());
        plan.setTargetCalories(request.getTargetCalories());
        plan.getMeals().clear();
        addMeals(plan, request.getMeals());

        return AppMapper.toNutritionPlan(nutritionPlanRepository.save(plan));
    }

    @Transactional
    public void delete(Long id) {
        User user = userService.getCurrentUser();
        NutritionPlan plan = nutritionPlanRepository.findByIdAndUserId(id, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Nutrition plan not found"));
        nutritionPlanRepository.delete(plan);
    }

    private void addMeals(NutritionPlan plan, List<MealRequest> meals) {
        if (meals == null) {
            return;
        }

        for (MealRequest mealRequest : meals) {
            Meal meal = Meal.builder()
                    .nutritionPlan(plan)
                    .name(mealRequest.getName())
                    .calories(mealRequest.getCalories())
                    .protein(mealRequest.getProtein())
                    .carbs(mealRequest.getCarbs())
                    .fat(mealRequest.getFat())
                    .build();
            plan.getMeals().add(meal);
        }
    }
}
