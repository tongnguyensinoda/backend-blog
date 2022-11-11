package com.tong.rmit.blogapis.services.impl;
import com.tong.rmit.blogapis.exceptions.*;
import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.UserDto;
import com.tong.rmit.blogapis.repositories.UserRepo;
import com.tong.rmit.blogapis.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);

    }



    @Override
    public UserDto updateUser(UserDto userDto, Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User"," Id ", user_id));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }



    @Override
    public UserDto getUserById(Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", user_id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", user_id));
        this.userRepo.delete(user);
    }

    public User dtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
//        User user = new User();
//        user.setUser_id(userDto.getUser_id());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setAbout(userDto.getAbout());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

//        UserDto userDto = new UserDto();
//        userDto.setUser_id(user.getUser_id());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setAbout(user.getAbout());
//        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
