package com.tong.rmit.blogapis.repositories;

import com.tong.rmit.blogapis.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
