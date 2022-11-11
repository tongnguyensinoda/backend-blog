package com.tong.rmit.blogapis.repositories;

import com.tong.rmit.blogapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    boolean existsById(Integer user_id);
    Optional<User> findByEmail(String email);
}
