package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.repository.PotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PotionService {

    private final PotionRepository potionRepository;

    private final StudentService studentService;
    private final RecipeService recipeService;

    public PotionService(PotionRepository potionRepository, StudentService studentService, RecipeService recipeService) {
        this.potionRepository = potionRepository;
        this.studentService = studentService;
        this.recipeService = recipeService;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }
}
