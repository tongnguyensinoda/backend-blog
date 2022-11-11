package com.tong.rmit.blogapis.repositories;

import com.tong.rmit.blogapis.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
