package com.tong.rmit.blogapis.repositories;

import com.tong.rmit.blogapis.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
