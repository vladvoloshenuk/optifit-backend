package com.optifit.backend.config;

import com.optifit.backend.model.Exercise;
import com.optifit.backend.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) {
        if (exerciseRepository.count() > 0) {
            return;
        }

        exerciseRepository.save(Exercise.builder()
                .name("Присідання")
                .muscleGroup("Ноги")
                .description("Базова вправа для ніг")
                .caloriesPerSet(15)
                .build());

        exerciseRepository.save(Exercise.builder()
                .name("Жим лежачи")
                .muscleGroup("Груди")
                .description("Вправа для грудних м'язів")
                .caloriesPerSet(12)
                .build());

        exerciseRepository.save(Exercise.builder()
                .name("Підтягування")
                .muscleGroup("Спина")
                .description("Вправа для спини")
                .caloriesPerSet(10)
                .build());

        exerciseRepository.save(Exercise.builder()
                .name("Планка")
                .muscleGroup("Кор")
                .description("Статична вправа для преса")
                .caloriesPerSet(8)
                .build());
    }
}
