package com.tong.rmit.blogapis.payloads;

import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.Comment;
import com.tong.rmit.blogapis.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class PostDto {

    private int post_id;
    @NotBlank
    @Size(min = 5, max = 30, message = "size of title is from 5 to 30 chars")
    private String title;

    @NotBlank
    @Size(min = 4, max = 1000, message = "size of content is from 4 to 1000 chars")
    private String content;

    private String imageName;
    private Date addDate;

    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments = new HashSet<>();

    public PostDto(){

    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
