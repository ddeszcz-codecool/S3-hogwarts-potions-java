package com.codecool.hogwarts_potions.handler;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;

import java.util.List;


public class PotionHandler {

    private final Potion potion;


    public PotionHandler(Potion potion) {
        this.potion = potion;
    }

    public void addIngredient(Ingredient ingredient){
        List<Ingredient> ingredientList = potion.getIngredients();
        ingredientList.add(ingredient);
        potion.setIngredients(ingredientList);
    }

}
