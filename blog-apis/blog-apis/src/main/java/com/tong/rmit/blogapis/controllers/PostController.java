package com.tong.rmit.blogapis.controllers;


import com.tong.rmit.blogapis.config.Constants;
import com.tong.rmit.blogapis.payloads.ApiResponse;
import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.payloads.PostResponse;
import com.tong.rmit.blogapis.services.PostService;
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
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = Constants.SPRT_DIR, required = false) String sortDir

    ){
        return ResponseEntity.ok(this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir));
    }

    // Get post by post id
    @GetMapping("/posts/{post_id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer post_id){
        return ResponseEntity.ok(this.postService.getPostById(post_id));
    }

    // Create a post - POST Method
    @PostMapping("/user/{user_id}/category/{cate_id}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer user_id, @PathVariable Integer cate_id) {
        PostDto createPostDto = this.postService.createPost(postDto, user_id, cate_id);
        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    // Get a post by user
    @GetMapping("/user/{user_id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer user_id){
        List<PostDto> posts = this.postService.getPostsByUser(user_id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Get a post by category
    @GetMapping("/category/{cate_id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer cate_id){
        List<PostDto> posts = this.postService.getPostsByCategory(cate_id);
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    @DeleteMapping("posts/{post_id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("post_id") Integer post_id){
        this.postService.deletePost(post_id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Post was deleted", true), HttpStatus.OK);
    }

    // Update a post - PUT Method
    @PutMapping("/posts/{post_id}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable("post_id") Integer post_id, @RequestBody PostDto postDto){
        PostDto updatedPost = this.postService.updatePost(postDto, post_id);
        return ResponseEntity.ok(updatedPost);
    }

    //Searching post
    @GetMapping("post/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);
    }


}
