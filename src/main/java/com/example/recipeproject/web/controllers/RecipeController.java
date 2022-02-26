package com.example.recipeproject.web.controllers;

import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.services.RecipeService;
import com.example.recipeproject.web.dto.RecipeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class RecipeController {

	RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping({"", "/"})
	public String getHomePage(Model model) {
		log.debug("Calling getHomePage in controller");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}

	@GetMapping("recipe/{id}/show")
	public String getRecipe(@PathVariable String id, Model model) {
		log.debug("Calling getRecipe in controller");
		model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String getRecipeForm(Model model){
		model.addAttribute("recipe", new RecipeDto());
		return "recipe/form";
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeDto recipeDto){
		Recipe savedRecipe = recipeService.saveRecipe(recipeDto);
		return "redirect:/recipe/" + savedRecipe.getId() + "/show";
	}

	@GetMapping("recipe/{id}/update")
	public String getUpdateRecipeForm(@PathVariable String id, Model model){
		model.addAttribute("recipe", recipeService.getRecipeById(Long.valueOf(id)));
		return "recipe/form";
	}

	@GetMapping("recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id, Model model){
		recipeService.deleteRecipeById(Long.valueOf(id));
		return "redirect:/";
	}
}
