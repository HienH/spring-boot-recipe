package com.example.recipeproject.web.mapper;
import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.domain.Note;
import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.web.dto.IngredientDto;
import com.example.recipeproject.web.dto.NoteDto;
import com.example.recipeproject.web.dto.RecipeDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RecipeMapper {

	private final IngredientMapper ingredientMapper;
	private final CategoryMapper categoryMapper;

	public RecipeMapper(IngredientMapper ingredientMapper, CategoryMapper categoryMapper) {
		this.ingredientMapper = ingredientMapper;
		this.categoryMapper = categoryMapper;
	}

	public Recipe recipeMapDtoToDomain(final RecipeDto recipeDto) {
		Set<Ingredient> ingredients = new HashSet<>();
		recipeDto.getIngredients().stream().map(ingredient -> ingredients.add(ingredientMapper.ingredientDtoToDomain(ingredient)));
		return Recipe.builder()
				.id(recipeDto.getId())
				.title(recipeDto.getTitle())
				.description(recipeDto.getDescription())
				.prepTime(recipeDto.getPrepTime())
				.cookTime(recipeDto.getCookTime())
				.serving(recipeDto.getServing())
				.source(recipeDto.getSource())
				.url(recipeDto.getUrl())
				.directions(recipeDto.getDirections())
				.note(Note.builder().recipeNote(recipeDto.getNote().getRecipeNote()).build())
				.difficulty(recipeDto.getDifficulty())
				.categories(recipeDto.getCategories())
				.ingredients(ingredients)
				.build();
	}

	// Mapper of what you want your web model to look like
	public RecipeDto recipeDomainToDto(Recipe recipe){
		Set<IngredientDto> ingredients = new HashSet<>();
		recipe.getIngredients().stream().forEach(ingredient -> ingredients.add(ingredientMapper.ingredientDomainToDto(ingredient)));

		return RecipeDto.builder()
				.id(recipe.getId())
				.title(recipe.getTitle())
				.description(recipe.getDescription())
				.prepTime(recipe.getPrepTime())
				.cookTime(recipe.getCookTime())
				.serving(recipe.getServing())
				.source(recipe.getSource())
				.url(recipe.getUrl())
				.directions(recipe.getDirections())
				.ingredients(ingredients)
				.difficulty(recipe.getDifficulty())
				.note(NoteDto.builder().recipeNote(recipe.getNote().getRecipeNote()).build())
				.categories(recipe.getCategories())
				.build();

	}
}