package com.tong.rmit.blogapis.services;

import com.tong.rmit.blogapis.payloads.CommentDto;
import com.tong.rmit.blogapis.payloads.UserDto;

import java.util.List;

public interface CommentService{

    CommentDto createComment(CommentDto commentDto, Integer post_id);
//    CommentDto updateComment(CommentDto user, Integer comment_id);
//    CommentDto getUserById(Integer comment_id);
//    List<UserDto> getAllUsers();
    void deleteComment(Integer comment_id);
}
