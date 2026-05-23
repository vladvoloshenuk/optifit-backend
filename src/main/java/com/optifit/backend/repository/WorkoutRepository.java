package com.optifit.backend.repository;

import com.optifit.backend.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("""
            SELECT DISTINCT w FROM Workout w
            LEFT JOIN FETCH w.exercises we
            LEFT JOIN FETCH we.exercise
            WHERE w.user.id = :userId
            ORDER BY w.workoutDate DESC
            """)
    List<Workout> findDetailedByUserId(@Param("userId") Long userId);

    @Query("""
            SELECT w FROM Workout w
            LEFT JOIN FETCH w.exercises we
            LEFT JOIN FETCH we.exercise
            WHERE w.id = :id AND w.user.id = :userId
            """)
    Optional<Workout> findDetailedByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    Optional<Workout> findByIdAndUserId(Long id, Long userId);

    long countByUserId(Long userId);
}
