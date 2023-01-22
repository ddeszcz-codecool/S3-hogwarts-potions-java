package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.Ingredient;
import com.codecool.hogwarts_potions.model.Recipe;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final StudentService studentService;

    public RecipeService(RecipeRepository recipeRepository, StudentService studentService) {
        this.recipeRepository = recipeRepository;
        this.studentService = studentService;
    }

    public boolean isRecipeAlreadyExists(String newPotionIngredientString) {

        List<String> allRecipeIngredients = recipeRepository.findAll().stream().map(recipe -> recipe.getIngredients().stream().map(Ingredient::getName).collect(Collectors.joining())).collect(Collectors.toList());

        for (String existingIngredientString : allRecipeIngredients) {
            if (existingIngredientString.equals(newPotionIngredientString)) {

                return true;
            }
        }

        return false;
    }

    public Recipe createRecipe(Long studentId, List<Ingredient> ingredients) {
        Recipe newRecipe = new Recipe();
        Student student = studentService.getStudentById(studentId);
        List<Recipe> recipesOfStudent = getRecipesByStudent(studentId);

        int studentsRecipes = recipesOfStudent != null ? recipesOfStudent.size() : 0;
        newRecipe.setStudent(student);
        newRecipe.setName(String.format("%s's discovery #%d", student.getName(), studentsRecipes + 1));
        newRecipe.setIngredients(ingredients);

        recipeRepository.saveAndFlush(newRecipe);
        return newRecipe;
    }

    private List<Recipe> getRecipesByStudent(Long id) {
        return recipeRepository.getRecipeByStudentId(id);
    }


    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
