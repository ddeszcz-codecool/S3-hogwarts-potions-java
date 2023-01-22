package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    //  @Query("SELECT name, id from Recipe where student.id = :studentId")
    List<Recipe> getRecipeByStudentId(Long id);

}
