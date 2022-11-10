package com.tong.rmit.blogapis.controllers;

import com.tong.rmit.blogapis.payloads.ApiResponse;
import com.tong.rmit.blogapis.payloads.CategoryDto;
import com.tong.rmit.blogapis.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getAllCategories());
}

    // Get a user by id
    @GetMapping("/{cate_id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer cate_id){
        return ResponseEntity.ok(this.categoryService.getCategoryById(cate_id));

    }

    // Create a new user - POST Method
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }
    // Update an user - PUT Method
    @PutMapping("/{cate_id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @PathVariable("cate_id") Integer cate_id, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, cate_id);
        return ResponseEntity.ok(updatedCategory);
    }

    // Delete an user - DEL Method
    @DeleteMapping("/{cate_id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("cate_id") Integer cate_id){
        this.categoryService.deleteCategory(cate_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted", true), HttpStatus.OK);
    }

}
