package com.example.recipeproject.bootstrap;

import com.example.recipeproject.domain.Difficulty;
import com.example.recipeproject.domain.Ingredient;
import com.example.recipeproject.domain.Note;
import com.example.recipeproject.domain.Recipe;
import com.example.recipeproject.repositories.CategoryRepository;
import com.example.recipeproject.repositories.IngredientRepository;
import com.example.recipeproject.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// used ApplicationListener as there is dependency on other beans
// can use command-line but arguments as String or ApplicationRunner - receive command-line arguments with names
// test with @EventListener
@Component
@Slf4j
public class LoadData implements ApplicationListener<ContextRefreshedEvent> {

	private CategoryRepository categoryRepository;
	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;

	public LoadData(CategoryRepository categoryRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	@Transactional // creates transaction to happen in same function (set in case lazy load error)
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		log.debug("Loaded recipe data");
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipes = new ArrayList<>();

		Recipe guacamole = new Recipe();
		guacamole.setTitle("Best guacamole");
		guacamole.setDescription("The word \"guacamole\" and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce)");
		guacamole.setPrepTime(10);
		guacamole.setCookTime(10);
		guacamole.setServing(4);
		guacamole.setSource("www.simplyrecipes.com");
		guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		guacamole.setDirections("" +
				"1 . Cut the avocado:\n" +
				"Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and " +
				"scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
				"\n 2.Mash the avocado flesh:\n" +
				"Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
				"\n 3. Add remaining ingredients to taste:\n" +
				"Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
				"\n" +
				"Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
				"\n" +
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
				"Start with this recipe and adjust to your taste." +
				"\n Serve immediately:\n" +
				"If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
				"\n" +
				"Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
				"\n" +
				"Refrigerate leftover guacamole up to 3 days.");

		Note guacNotes = new Note();
		guacNotes.setRecipeNote("Simple Guacamole: The simplest version of guacamole is just mashed avocados with " +
				"salt. Don't" +
				" " +
				"let the lack of other ingredients stop you from making guacamole.\n" +
				"Quick guacamole: For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
				"Don't have enough avocados? To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It still tastes great.");

		/// SET BIDIRECTION RELATIONSHIP IN SETTER
		guacamole.setNote(guacNotes);
		guacamole.setDifficulty(Difficulty.EASY);
		// use category repo to set category
		guacamole.getCategories().add(categoryRepository.findByCategoryName("Mexican").get());
		guacamole.getCategories().add(categoryRepository.findByCategoryName("American").get());

		/// SET BIDIRECTION RELATIONSHIP IN ADD INGREDIENT FUNCTION BELOW
//		Set<Ingredient> ingredientList = new HashSet<>();
//		Ingredient avo = new Ingredient("avocados", new BigDecimal(2));
//		ingredientList.add(avo);
//		Ingredient salt = new Ingredient("salt", new BigDecimal(0.25));
//		ingredientList.add(salt);
//		Ingredient lemon = new Ingredient("lemon", new BigDecimal(1));
//		ingredientList.add(lemon);

//		ingredientRepository.saveAll(ingredientList);



//		guacamole.addIngredient(new Ingredient("avocados", new BigDecimal(2)));
//		guacamole.addIngredient(new Ingredient("salt", new BigDecimal(0.25)));
//		guacamole.addIngredient(new Ingredient("lemon", new BigDecimal(1)));


		guacamole.addIngredient(Ingredient.builder().amount(new BigDecimal(2)).description("avocados").build());
		guacamole.addIngredient(Ingredient.builder().amount(new BigDecimal(0.25)).description("salt").build());
		guacamole.addIngredient(Ingredient.builder().amount(new BigDecimal(1)).description("lemon").build());

		recipes.add(guacamole);
		Recipe recipe2 = new Recipe();

		recipe2.setTitle("Recipe 2");
		recipe2.setDescription("recipe 2");
		recipe2.setPrepTime(100);
		recipe2.setCookTime(100);
		recipe2.setServing(3);
		recipe2.setSource("www.simplyrecipes.com");
		recipe2.setDescription("Cut the avocado");

		Note recipe2Notes = new Note();
		recipe2Notes.setRecipeNote("Simple Guacamole");

		/// SET BIDIRECTION RELATIONSHIP IN SETTER
		recipe2.setNote(recipe2Notes);
		recipe2.getCategories().add(categoryRepository.findByCategoryName("Mexican").get());
		recipe2.setDifficulty(Difficulty.EASY);
		recipe2.addIngredient(Ingredient.builder().amount(new BigDecimal(2)).description("peach").build());
		recipes.add(recipe2);


		return recipes;
	}

}
