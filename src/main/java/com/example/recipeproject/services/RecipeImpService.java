package com.example.recipeproject.services;

import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.repositories.RecipeRepository;
import com.example.recipeproject.web.dto.RecipeDto;
import com.example.recipeproject.web.mapper.RecipeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeImpService implements RecipeService {
	RecipeRepository recipeRepository;
	RecipeMapper recipeMapper;

	public RecipeImpService(RecipeRepository recipeRepository, RecipeMapper recipeMapper) {
		this.recipeRepository = recipeRepository;
		this.recipeMapper = recipeMapper;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> allRecipes = new HashSet<>();
		recipeRepository.findAll()
				.forEach(allRecipes::add);
		return allRecipes;
	}

	@Override
	public Recipe getRecipeById(Long id) {
		log.debug("Calling getRecipeBy Id in Service");
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if (!recipe.isPresent()) {
			// add exception
			return null;
		}
		return recipe.get();
	}

	@Override
	public Recipe saveRecipe(RecipeDto recipe) {
		log.debug("adding/ udpating new recipe in Service");
		Recipe recipeToAdd = recipeMapper.recipeMapDtoToDomain(recipe);
		Recipe recipeSaved =  recipeRepository.save(recipeToAdd);
		return  recipeRepository.save(recipeToAdd);
	}

	@Override
	public RecipeDto getRecipeDtoById(Long id) {
		log.debug("Calling getRecipeBy Id in Service");
		Optional<Recipe> recipe = recipeRepository.findById(id);
		if (!recipe.isPresent()) {
			// add exception
			return null;
		}
		return recipeMapper.recipeDomainToDto(recipe.get());
	}

	@Override
	public void deleteRecipeById(Long id) {
		recipeRepository.deleteById(id);
	}
}
