package com.tong.rmit.blogapis.services;

import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.payloads.CategoryDto;
import com.tong.rmit.blogapis.payloads.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer cate_id);
    CategoryDto getCategoryById(Integer cate_id);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Integer cate_id);
}
