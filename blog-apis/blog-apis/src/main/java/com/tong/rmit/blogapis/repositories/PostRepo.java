package com.tong.rmit.blogapis.repositories;

import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.Post;
import com.tong.rmit.blogapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
