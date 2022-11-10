package com.tong.rmit.blogapis.services;

import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    UserDto updateUser(UserDto user, Integer user_id);
    UserDto getUserById(Integer user_id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer user_id);
}
