package com.tong.rmit.blogapis.services.impl;

import com.tong.rmit.blogapis.exceptions.ResourceNotFoundException;
import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.Post;
import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.CategoryDto;
import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.repositories.CategoryRepo;
import com.tong.rmit.blogapis.repositories.PostRepo;
import com.tong.rmit.blogapis.repositories.UserRepo;
import com.tong.rmit.blogapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer user_id, Integer cate_id) {

        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id", user_id));
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id", cate_id));

        Post post = this.dtoToPost(postDto);
        post.setImageName("hi.png");
//        post.setAddDate(new Date());
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);


        Post savedPost = this.postRepo.save(post);

        return this.postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id", post_id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatePost = this.postRepo.save(post);
        PostDto postDto1 = this.postToDto(updatePost);

        return postDto1;
    }

    @Override
    public PostDto getPostById(Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id", post_id));
        return this.postToDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public void deletePost(Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id ", post_id));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer cate_id) {
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id ", cate_id));

        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", user_id));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    public Post dtoToPost(PostDto postDto){
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto postToDto(Post post){
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
