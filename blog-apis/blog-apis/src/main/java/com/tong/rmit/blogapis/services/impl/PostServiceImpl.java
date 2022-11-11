package com.tong.rmit.blogapis.services.impl;

import com.tong.rmit.blogapis.exceptions.ResourceNotFoundException;
import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.Post;
import com.tong.rmit.blogapis.models.User;
import com.tong.rmit.blogapis.payloads.CategoryDto;
import com.tong.rmit.blogapis.payloads.PostResponse;
import org.springframework.data.domain.Pageable;
import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.repositories.CategoryRepo;
import com.tong.rmit.blogapis.repositories.PostRepo;
import com.tong.rmit.blogapis.repositories.UserRepo;
import com.tong.rmit.blogapis.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    // Method - Create a new post
    @Override
    public PostDto createPost(PostDto postDto, Integer user_id, Integer cate_id) {

        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id", user_id));
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id", cate_id));

        Post post = this.dtoToPost(postDto);
        post.setImageName("hi.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = this.postRepo.save(post);

        return this.postToDto(savedPost);
    }

    // Method - Update a post
    @Override
    public PostDto updatePost(PostDto postDto, Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id", post_id));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatePost = this.postRepo.save(post);
        PostDto postDto1 = this.postToDto(updatePost);

        return postDto1;
    }

    // Method - Get post by id
    @Override
    public PostDto getPostById(Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id", post_id));
        return this.postToDto(post);
    }

    // Method - get all posts
    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
//        if(sortDir.equalsIgnoreCase("asc"))
//        {
//            sort=Sort.by(sortBy).ascending();
//        } else {
//            sort=Sort.by(sortBy).descending();
//        }
        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Post> pagePost= this.postRepo.findAll(p);

        List<Post> posts = pagePost.getContent();
//        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    // Method - Delete a post
    @Override
    public void deletePost(Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id ", post_id));
        this.postRepo.delete(post);
    }

    // Method - Get all post of a category
    @Override
    public List<PostDto> getPostsByCategory(Integer cate_id) {
        Category category = this.categoryRepo.findById(cate_id).orElseThrow(()-> new ResourceNotFoundException("Category", " Id ", cate_id));

        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    // Method - Get all posts of a user
    @Override
    public List<PostDto> getPostsByUser(Integer user_id) {
        User user = this.userRepo.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", user_id));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    // Method - Search post by id
    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
        List<PostDto> postDtos= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
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
