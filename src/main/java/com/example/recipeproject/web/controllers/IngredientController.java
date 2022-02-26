package com.example.recipeproject.web.controllers;

import com.example.recipeproject.services.IngredientService;
import com.example.recipeproject.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class IngredientController {
	IngredientService ingredientService;
	RecipeService recipeService;

	public IngredientController(IngredientService ingredientService, RecipeService recipeService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
	}

	@GetMapping("/{recipeId}/ingredients")
	public String getRecipeById(Model model, @PathVariable Long recipeId){
		model.addAttribute("recipe", recipeService.getRecipeById(recipeId));
		return "recipe/ingredients/show";
	}

	@GetMapping("/{recipeId}/ingredient/{ingredientId}")
	public String deleteIngredientById(@PathVariable String recipeId,@PathVariable String ingredientId){
		ingredientService.deleteIngredientById(Long.valueOf(recipeId),Long.valueOf(ingredientId));
		return "redirect:recipe/ingredients/show";
	}

}
