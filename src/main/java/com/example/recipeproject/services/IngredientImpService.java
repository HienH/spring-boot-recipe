package com.example.recipeproject.services;

import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.repositories.IngredientRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.web.dto.IngredientDto;
import com.example.recipeproject.web.mapper.IngredientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class IngredientImpService implements IngredientService {
	RecipeRepository recipeRepository;

	public IngredientImpService(RecipeRepository recipeRepository, IngredientMapper ingredientMapper) {
		this.recipeRepository = recipeRepository;
		this.ingredientMapper = ingredientMapper;
	}

	IngredientMapper ingredientMapper;
// here
	@Override
	public IngredientDto getIngredientById(Long recipeId, Long ingredientId) {
		log.debug("calling get ingredients by recipeId service");
		IngredientDto ingredientsDTOByRecipeId = new IngredientDto();

		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isPresent()) {
			Ingredient ingredient = recipe.get().getIngredients().stream().filter(ing -> ing.getId().equals(ingredientId)).findAny().orElse(null);
			ingredientsDTOByRecipeId = ingredientMapper.ingredientDomainToDto(ingredient);
		} else {
			log.debug("recipe not found");
		}

		return ingredientsDTOByRecipeId;
	}

	@Override
	public void deleteIngredientById(Long recipeId, Long ingredientId) {
		log.debug("deleting ingredient id "+ ingredientId);
		Optional<Recipe> recipe = recipeRepository.findById(recipeId);
		if (recipe.isPresent()) {
			Optional<Ingredient> ingredientToDelete =
					recipe.get().getIngredients().stream().filter(ing -> ing.getId().equals(ingredientId)).findFirst();

			if(ingredientToDelete.isPresent()){
				ingredientToDelete.get().setRecipe(null);
				recipe.get().getIngredients().remove(ingredientToDelete.get());
				recipeRepository.save(recipe.get());
			}
		} else {
			log.debug("Recipe not found "+ recipeId);
			// recipe nto found
		}
	}
}
