package com.tong.rmit.blogapis.payloads;

import com.tong.rmit.blogapis.models.Category;
import com.tong.rmit.blogapis.models.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class PostDto {

    @NotBlank
    @Size(max = 4, message = "min size of post is 20")
    private String title;

    @NotBlank
    @Size(max = 4, message = "min size of post is 1000")
    private String content;

    private String imageName;
    private Date addDate;

    private Category category;
    private User user;

    public PostDto(){

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
