package com.tong.rmit.blogapis.controllers;


import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.payloads.UserDto;
import com.tong.rmit.blogapis.services.PostService;
import com.tong.rmit.blogapis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // Get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(this.postService.getAllPosts());
    }

    // Get post by post id
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer post_id){
        return ResponseEntity.ok(this.postService.getPostById(post_id));

    }

    // Create a new user - POST Method
    @PostMapping("/user/{user_id}/category/{cate_id}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer user_id, @PathVariable Integer cate_id) {
        PostDto createPostDto = this.postService.createPost(postDto, user_id, cate_id);
        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    // Get post by user
    @GetMapping("/user/{user_id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer user_id){
        List<PostDto> posts = this.postService.getPostsByUser(user_id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }


    @GetMapping("/category/{cate_id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer cate_id){
        List<PostDto> posts = this.postService.getPostsByCategory(cate_id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

}
