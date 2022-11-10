package com.tong.rmit.blogapis.services;

import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.payloads.UserDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer user_id, Integer cate_id);

    PostDto updatePost(PostDto postDto, Integer post_id);

    PostDto getPostById(Integer post_id);

    List<PostDto> getAllPosts();

    void deletePost(Integer post_id);

    // Get post by category
    List<PostDto> getPostsByCategory(Integer cate_id);

    // get post by user
    List<PostDto> getPostsByUser(Integer post_id);
    // search post

    List<PostDto> searchPosts(String keyword);


}
