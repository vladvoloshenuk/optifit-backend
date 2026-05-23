package com.optifit.backend.repository;

import com.optifit.backend.model.NutritionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NutritionPlanRepository extends JpaRepository<NutritionPlan, Long> {

    @Query("""
            SELECT DISTINCT p FROM NutritionPlan p
            LEFT JOIN FETCH p.meals
            WHERE p.user.id = :userId
            ORDER BY p.planDate DESC
            """)
    List<NutritionPlan> findDetailedByUserId(@Param("userId") Long userId);

    @Query("""
            SELECT p FROM NutritionPlan p
            LEFT JOIN FETCH p.meals
            WHERE p.id = :id AND p.user.id = :userId
            """)
    Optional<NutritionPlan> findDetailedByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    Optional<NutritionPlan> findByIdAndUserId(Long id, Long userId);

    long countByUserId(Long userId);
}
