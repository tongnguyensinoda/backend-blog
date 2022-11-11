package com.tong.rmit.blogapis.services.impl;

import com.tong.rmit.blogapis.exceptions.ResourceNotFoundException;
import com.tong.rmit.blogapis.models.Comment;
import com.tong.rmit.blogapis.models.Post;
import com.tong.rmit.blogapis.payloads.CommentDto;
import com.tong.rmit.blogapis.payloads.PostDto;
import com.tong.rmit.blogapis.repositories.CommentRepo;
import com.tong.rmit.blogapis.repositories.PostRepo;
import com.tong.rmit.blogapis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer post_id) {
        Post post = this.postRepo.findById(post_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id", post_id));

        Comment comment = this.dtoToComment(commentDto);
        comment.setPost(post);

        Comment addedComment = this.commentRepo.save(comment);
        return this.commentToDto(addedComment);
    }

    @Override
    public void deleteComment(Integer comment_id) {
        Comment comment = this.commentRepo.findById(comment_id).orElseThrow(()-> new ResourceNotFoundException("Post", " Id ", comment_id));
        this.commentRepo.delete(comment);


    }

    public Comment dtoToComment(CommentDto commentDto){
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        return comment;
    }


    public CommentDto commentToDto(Comment comment){
        CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }


}
