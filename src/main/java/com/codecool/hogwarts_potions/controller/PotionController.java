package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.service.PotionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PotionController {
    PotionService potionService;

    public PotionController(PotionService potionService) {
        this.potionService = potionService;
    }


    @GetMapping("/potions")
    public List<Potion> getAllPotions(){
        return potionService.getAllPotions();
    }


    @PostMapping("/potion/brew/{id}")
    public Potion brewPotion(@PathVariable("id") Long id, @RequestBody List<Ingredient> ingredients) {
        return potionService.brewPotion(id, ingredients);
    }

    @GetMapping("/{id}")
    public List<Potion> getAllPotionsForStudent(@PathVariable ("id") Long id){
        return potionService.getPotionsByStudent(id);
    }




}