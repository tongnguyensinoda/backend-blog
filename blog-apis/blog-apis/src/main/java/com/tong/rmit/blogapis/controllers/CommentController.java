package com.tong.rmit.blogapis.controllers;


import com.tong.rmit.blogapis.payloads.ApiResponse;
import com.tong.rmit.blogapis.payloads.CommentDto;
import com.tong.rmit.blogapis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{post_id}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer post_id)
    {
        CommentDto createComment = this.commentService.createComment(comment, post_id);
        return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer comment_id)
    {
        this.commentService.deleteComment(comment_id);
        return new ResponseEntity<>(new ApiResponse("Comment deleted", true), HttpStatus.OK);
    }

}
