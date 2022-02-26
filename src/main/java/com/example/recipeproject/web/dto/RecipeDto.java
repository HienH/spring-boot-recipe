package com.example.recipeproject.web.dto;

import com.example.recipeproject.domain.Category;
import com.example.recipeproject.domain.Difficulty;
import com.example.recipeproject.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//DataTransferObject
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RecipeDto {
	private Long id;
	private String title;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientDto> ingredients = new HashSet<>();
	private Difficulty difficulty;
	private NoteDto note;
	private Set<Category> categories = new HashSet<>();
}

