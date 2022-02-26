package com.example.recipeproject.repositories;

import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}