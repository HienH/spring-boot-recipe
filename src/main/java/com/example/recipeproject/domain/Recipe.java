package com.example.recipeproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Lob
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	@Lob
	private String directions;
	@Lob
	private Byte[] image;
	@OneToOne(cascade = CascadeType.ALL)
	private Note note;
	@Enumerated(value = EnumType.STRING)
	private Difficulty difficulty;

	@ManyToMany
	@JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns =
	@JoinColumn(name =
			"category_id"))
	private Set<Category> categories = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "recipe_id")
	private Set<Ingredient> ingredients = new HashSet<>();


	public void setNote(Note note) {
		this.note = note;
		// set bidirection of recipe to note
		note.setRecipe(this);
	}

	public Recipe addIngredient(Ingredient addedIngredient) {
		this.getIngredients().add(addedIngredient);
		addedIngredient.setRecipe(this);
		return this;
	}

	public Long getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public Integer getPrepTime() {
		return this.prepTime;
	}

	public Integer getCookTime() {
		return this.cookTime;
	}

	public Integer getServing() {
		return this.serving;
	}

	public String getSource() {
		return this.source;
	}

	public String getUrl() {
		return this.url;
	}

	public String getDirections() {
		return this.directions;
	}

	public Byte[] getImage() {
		return this.image;
	}

	public Note getNote() {
		return this.note;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public Set<Category> getCategories() {
		return this.categories;
	}

	public Set<Ingredient> getIngredients() {
		return this.ingredients;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}

	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}

	public void setServing(Integer serving) {
		this.serving = serving;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof Recipe)) return false;
		final Recipe other = (Recipe) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$id = this.getId();
		final Object other$id = other.getId();
		if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
		final Object this$title = this.getTitle();
		final Object other$title = other.getTitle();
		if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
		final Object this$description = this.getDescription();
		final Object other$description = other.getDescription();
		if (this$description == null ? other$description != null : !this$description.equals(other$description))
			return false;
		final Object this$prepTime = this.getPrepTime();
		final Object other$prepTime = other.getPrepTime();
		if (this$prepTime == null ? other$prepTime != null : !this$prepTime.equals(other$prepTime)) return false;
		final Object this$cookTime = this.getCookTime();
		final Object other$cookTime = other.getCookTime();
		if (this$cookTime == null ? other$cookTime != null : !this$cookTime.equals(other$cookTime)) return false;
		final Object this$serving = this.getServing();
		final Object other$serving = other.getServing();
		if (this$serving == null ? other$serving != null : !this$serving.equals(other$serving)) return false;
		final Object this$source = this.getSource();
		final Object other$source = other.getSource();
		if (this$source == null ? other$source != null : !this$source.equals(other$source)) return false;
		final Object this$url = this.getUrl();
		final Object other$url = other.getUrl();
		if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
		final Object this$directions = this.getDirections();
		final Object other$directions = other.getDirections();
		if (this$directions == null ? other$directions != null : !this$directions.equals(other$directions))
			return false;
		if (!java.util.Arrays.deepEquals(this.getImage(), other.getImage())) return false;
		final Object this$note = this.getNote();
		final Object other$note = other.getNote();
		if (this$note == null ? other$note != null : !this$note.equals(other$note)) return false;
		final Object this$difficulty = this.getDifficulty();
		final Object other$difficulty = other.getDifficulty();
		if (this$difficulty == null ? other$difficulty != null : !this$difficulty.equals(other$difficulty))
			return false;
		final Object this$categories = this.getCategories();
		final Object other$categories = other.getCategories();
		if (this$categories == null ? other$categories != null : !this$categories.equals(other$categories))
			return false;
		final Object this$ingredients = this.getIngredients();
		final Object other$ingredients = other.getIngredients();
		if (this$ingredients == null ? other$ingredients != null : !this$ingredients.equals(other$ingredients))
			return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof Recipe;
	}


}
