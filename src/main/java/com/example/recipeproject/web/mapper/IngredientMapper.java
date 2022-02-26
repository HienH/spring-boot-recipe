package com.example.recipeproject.web.mapper;

import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.web.dto.IngredientDto;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
	public Ingredient ingredientDtoToDomain(IngredientDto ingredientDto) {
		return  Ingredient.builder().description(ingredientDto.getDescription()).amount(ingredientDto.getAmount()).build();
	}

	public IngredientDto ingredientDomainToDto(Ingredient ingredient) {
		return  IngredientDto.builder().id(ingredient.getId()).description(ingredient.getDescription()).amount(ingredient.getAmount()).build();
	}
}
