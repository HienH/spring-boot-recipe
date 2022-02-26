package com.example.recipeproject.services;

import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.web.dto.RecipeDto;

import java.util.Set;

public interface RecipeService {
	Set<Recipe> getRecipes();

	Recipe getRecipeById(Long id);

	Recipe saveRecipe(RecipeDto recipeDto);

	RecipeDto getRecipeDtoById(Long id);

	void deleteRecipeById(Long id);
}
