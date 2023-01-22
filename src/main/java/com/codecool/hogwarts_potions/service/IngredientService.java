package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> sortIngredient(List<Ingredient> unsortedIngredients) {

        return unsortedIngredients.stream().sorted(Comparator.comparing(Ingredient::getName)).collect(Collectors.toList());

    }

    public List<Ingredient> getPersistentList(List<Ingredient> ingredients) {
        List<Ingredient> properList = new ArrayList<>();
        for (Ingredient newPotionIngredient : ingredients) {
            if (!ingredientRepository.existsByName(newPotionIngredient.getName())) {
                ingredientRepository.saveAndFlush(newPotionIngredient);
            }

            properList.add(ingredientRepository.getIngredientByName(newPotionIngredient.getName()));
        }
        return properList;
    }

    public boolean hasNewIngredient(List<Ingredient> ingredients) {
        boolean isHasNew = false;
        for (Ingredient newPotionIngredient : ingredients) {
            if (!ingredientRepository.existsByName(newPotionIngredient.getName())) {
                isHasNew = true;
                ingredientRepository.saveAndFlush(newPotionIngredient);
            }
        }
        return isHasNew;
    }

    public Ingredient getPersistentIngredientToPotion(Ingredient ingredient) {
        String ingredientName = ingredient.getName();
        ingredient.setName(ingredient.getName().toLowerCase());

        if (!ingredientRepository.existsByName(ingredientName)) {
            ingredientRepository.saveAndFlush(ingredient);
        }
        return ingredientRepository.getIngredientByName(ingredientName);

    }
}
