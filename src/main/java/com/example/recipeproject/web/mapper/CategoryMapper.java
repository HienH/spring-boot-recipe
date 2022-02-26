package com.example.recipeproject.web.mapper;

import com.example.recipeproject.domain.Category;
import com.example.recipeproject.web.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

	public Category categoryDtoToDomain(CategoryDto categoryDto) {
		return Category.builder().id(categoryDto.getId()).categoryName(categoryDto.getCategoryName()).build();
	}

	public CategoryDto categoryDomainToDto(Category category){
		return CategoryDto.builder().id(category.getId()).categoryName(category.getCategoryName()).build();
	}
}
