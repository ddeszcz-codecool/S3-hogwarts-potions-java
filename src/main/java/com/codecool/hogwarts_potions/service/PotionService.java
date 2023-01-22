package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.BrewingStatus;
import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Potion;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.repository.PotionRepository;
import com.codecool.hogwarts_potions.service.constants.BrewingServiceConstants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PotionService {

    private final PotionRepository potionRepository;

    private final StudentService studentService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;


    public PotionService(PotionRepository potionRepository, StudentService studentService, RecipeService recipeService, IngredientService ingredientService) {
        this.potionRepository = potionRepository;
        this.studentService = studentService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    public List<Potion> getAllPotions() {
        return potionRepository.findAll();
    }

    public Potion brewPotion(Long studentId, List<Ingredient> ingredients) {

        if (ingredients.size() > BrewingServiceConstants.MAX_INGREDIENTS_FOR_POTIONS) {
            return null;
        }

//        ingredients.forEach(ingredient -> ingredient.setName(ingredient.getName().toLowerCase()));


        List<Ingredient> sortedIngredients = ingredientService.sortIngredient(ingredients);

        List<Ingredient> persistentList = ingredientService.getPersistentList(sortedIngredients);

        String ingredientString = createIngredientString(sortedIngredients);
        System.out.println("Location PotionService.brewPotion -> sout is displaying ingredient list as a String. Ingredient list: " + ingredientString); //todo to be removed integration testing
        System.out.println("Location PotionService.brewPotion -> sout is displaying ingredient studentId added: " + studentId); //todo to be removed integration testing

        Potion newPotion = createNewPotion(studentId);
        if (ingredients.size() < BrewingServiceConstants.MAX_INGREDIENTS_FOR_POTIONS) {
            newPotion.setBrewingStatus(BrewingStatus.BREW);
        } else {
            findBrewingStatus(newPotion, studentId, persistentList, sortedIngredients, ingredientString);
        }

        newPotion.setIngredients(persistentList);
        potionRepository.saveAndFlush(newPotion);
        return newPotion;

    }

    private String createIngredientString(List<Ingredient> ingredients) {
        return ingredients.stream().map(Ingredient::getName).collect(Collectors.joining());
    }

    private Potion createNewPotion(Long studentId) {
        Potion newPotion = new Potion();
        newPotion.setBrewerStudent(studentService.getStudentById(studentId));

        return newPotion;
    }

    private void findBrewingStatus(Potion newPotion, Long studentId, List<Ingredient> persistentList, List<Ingredient> sortedIngredients, String ingredientString) {
        if (ingredientService.hasNewIngredient(sortedIngredients) || !recipeService.isRecipeAlreadyExists(ingredientString)) {
            setDiscoveryBrewingStatus(newPotion, studentId, persistentList);
        } else {
            setReplicaBrewingStatus(newPotion);
        }
    }

    private void setDiscoveryBrewingStatus(Potion potion, Long studentId, List<Ingredient> ingredients) {

        potion.setBrewingStatus(BrewingStatus.DISCOVERY);
        Recipe newRecipe = recipeService.createRecipe(studentId, ingredients);
        potion.setRecipe(newRecipe);
        potion.setName(newRecipe.getName());
    }

    private void setReplicaBrewingStatus(Potion potion) {
        potion.setBrewingStatus(BrewingStatus.REPLICA);
        potion.setName(String.format("%s's replica", potion.getBrewerStudent().getName()));
    }

}
