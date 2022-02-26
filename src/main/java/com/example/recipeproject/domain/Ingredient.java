package com.example.recipeproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.Objects;

// Can use Lombok to refactor but will leave this to be used as an alternative example
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;


	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

//	public Ingredient(String description, BigDecimal amount) {
//		this.description = description;
//		this.amount = amount;
//	}

//	public Ingredient() {
//
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Ingredient)) return false;
		Ingredient that = (Ingredient) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getRecipe(), that.getRecipe());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDescription(), getAmount(), getRecipe());
	}

	@Override
	public String toString() {
		return "Ingredient{" +
				"id=" + id +
				", description='" + description + '\'' +
				", amount=" + amount +
				", recipe=" + recipe +
				'}';
	}
}
