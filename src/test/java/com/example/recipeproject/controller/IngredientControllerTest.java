package com.example.recipeproject.controller;

import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.services.IngredientService;
import com.example.recipeproject.services.RecipeService;
import com.example.recipeproject.web.controllers.IngredientController;
import com.example.recipeproject.web.dto.IngredientDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IngredientController.class)
public class IngredientControllerTest {
	@MockBean
	IngredientService ingredientService;

	@MockBean
	RecipeService recipeService;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	void setUp() {

	}

	@AfterEach
	void cleanUp() {
		verifyNoMoreInteractions(ingredientService);
	}

	@Test
	void getRecipeById() throws Exception {
		Long id = 1L;
		Recipe recipe = Recipe.builder().build();
		when(recipeService.getRecipeById(anyLong())).thenReturn(recipe);
		this.mockMvc.perform(get("/recipe/{recipeId}/ingredients", id))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"));

		verify(recipeService).getRecipeById(id);
	}

	@Test
	void deleteIngredientsById() throws Exception {
		Long recipeId = 2L;
		Long ingredId = 2L;

		this.mockMvc.perform(get("/recipe/{recipeId}/ingredient/{ingredId}/delete", recipeId, ingredId))
				.andExpect(status().is3xxRedirection());
		verify(ingredientService).deleteIngredientById(recipeId,ingredId);
	}
	@Test
	void getIngredientById() throws Exception {
		Long id = 2L;
		Long ingredId = 1L;
		IngredientDto ingredientDto = IngredientDto.builder().build();
		when(ingredientService.getIngredientById(id, ingredId)).thenReturn(ingredientDto);
		this.mockMvc.perform(get("/recipe/{recipeId}/ingredient/{ingredientId}/update", id, ingredId))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(view().name("recipe/ingredients/form"));

		verify(ingredientService).getIngredientById(id, ingredId);

	}
}
