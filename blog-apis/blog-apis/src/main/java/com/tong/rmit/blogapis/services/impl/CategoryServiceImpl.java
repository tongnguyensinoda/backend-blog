package com.tong.rmit.blogapis.services.impl;

import com.tong.rmit.blogapis.exceptions.ResourceNotFoundException;
import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.CategoryDto;
import com.tong.rmit.blogapis.repositories.CategoryRepo;
import com.tong.rmit.blogapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Method - Create a new category
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToCategory(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);
        return this.categoryToDto(savedCategory);
    }

    // Method - Update a category
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer cate_id) {
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id", cate_id));

        category.setCate_title(categoryDto.getCate_title());
        category.setCate_description(categoryDto.getCate_description());

        Category updateCategory = this.categoryRepo.save(category);
        CategoryDto categoryDto1 = this.categoryToDto(updateCategory);
        return categoryDto1;
    }

    // Method - Get category by id
    @Override
    public CategoryDto getCategoryById(Integer cate_id) {
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id", cate_id));
        return this.categoryToDto(category);
    }

    // Method - get all categories
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.categoryToDto(category)).collect(Collectors.toList());
    return categoryDtos;
    }

    // Method - Delete a category
    @Override
    public void deleteCategory(Integer cate_id) {
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id", cate_id));
        this.categoryRepo.delete(category);

    }

    // save data from dto to category model
    public Category dtoToCategory(CategoryDto categoryDto){
        Category category = this.modelMapper.map(categoryDto, Category.class);

        return category;
    }

    // get data from category model to dto
    public CategoryDto categoryToDto(Category category){
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);

        return categoryDto;
    }
}
