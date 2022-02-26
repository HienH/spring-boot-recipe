package com.example.recipeproject.controller;

import com.example.recipeproject.domain.Note;
import com.example.recipeproject.web.controllers.RecipeController;
import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.services.RecipeService;
import com.example.recipeproject.web.dto.RecipeDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(RecipeController.class)
public class RecipeControllerTest {

	@MockBean
	RecipeService recipeService;

	@Autowired
	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void cleanUp() {
		verifyNoMoreInteractions(recipeService);
	}

	@Test
	void getHomePage() throws Exception {
		Set<Recipe> recipesList = new HashSet<>();

		when(recipeService.getRecipes()).thenReturn(recipesList);

		this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipes"));
		verify(recipeService).getRecipes();
	}

	@Test
	void getRecipeById() throws Exception {
		Long id = 1L;
		Note note = new Note();
		Recipe recipe = Recipe.builder().id(id).build();
		recipe.setNote(note);
		when(recipeService.getRecipeById(id)).thenReturn(recipe);

		mockMvc.perform(get("/recipe/{id}/show", id))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));
		verify(recipeService).getRecipeById(1L);
	}

	@Test
	void getRecipeForm() throws Exception {
		mockMvc.perform(get("/recipe/new").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/form"));
	}

	@Test
	void saveOrUpdate() throws Exception {
		Recipe recipe = Recipe.builder().id(1L).build();
		when(recipeService.saveRecipe(any())).thenReturn(recipe);
		mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/1/show"));
		verify(recipeService).saveRecipe(any(RecipeDto.class));
	}

	@Test
	void getUpdateRecipeForm() throws Exception {
		Long id = 5L;
		RecipeDto updatedRecipe = RecipeDto.builder().build();
		when(recipeService.getRecipeDtoById(id)).thenReturn(updatedRecipe);

		mockMvc.perform(get("/recipe/{id}/update", id).contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"))
				.andExpect(view().name("recipe/form"));
		verify(recipeService.getRecipeDtoById(id));
	}

	@Test
	void deleteRecipe() throws Exception {
		Long id  = 9L;
		mockMvc.perform(get("/recipe/{id}/delete", id))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));
		verify(recipeService, times(1)).deleteRecipeById(anyLong());

	}
}
