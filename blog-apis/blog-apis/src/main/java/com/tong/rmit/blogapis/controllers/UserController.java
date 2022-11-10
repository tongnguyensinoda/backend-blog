package com.tong.rmit.blogapis.controllers;

import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.ApiResponse;
import com.tong.rmit.blogapis.payloads.UserDto;
import com.tong.rmit.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    // Get a user by id
    @GetMapping("/{user_id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer user_id){
        return ResponseEntity.ok(this.userService.getUserById(user_id));

    }

    // Create a new user - POST Method
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    // Update an user - PUT Method
    @PutMapping("/{user_id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("user_id") Integer user_id, @RequestBody UserDto userDto){
        UserDto updatedUser = this.userService.updateUser(userDto, user_id);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete an user - DEL Method
    @DeleteMapping("/{user_id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("user_id") Integer user_id){
        this.userService.deleteUser(user_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted", true), HttpStatus.OK);
    }
}
