package com.example.recipeproject.services;

import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.repositories.IngredientRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.web.dto.IngredientDto;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface IngredientService {
	IngredientDto getIngredientById(Long recipeId, Long ingredientId);

	void deleteIngredientById(Long recipeId,Long ingredientId);
}