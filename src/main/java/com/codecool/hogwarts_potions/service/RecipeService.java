package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final StudentService studentService;

    public RecipeService(RecipeRepository recipeRepository, StudentService studentService) {
        this.recipeRepository = recipeRepository;
        this.studentService = studentService;
    }
}
