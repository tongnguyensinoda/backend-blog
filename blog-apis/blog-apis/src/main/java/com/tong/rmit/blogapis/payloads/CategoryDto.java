package com.tong.rmit.blogapis.payloads;

import com.tong.rmit.blogapis.repositories.CategoryRepo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoryDto {

    private int cate_id;
    @NotBlank
    @Size(min = 4, message = "min size of category is 4")
    private String cate_title;

    @NotBlank
    @Size(min = 10, max = 20, message = "The description should be in 10-20 character")
    private String cate_description;

    public CategoryDto(){

    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_title() {
        return cate_title;
    }

    public void setCate_title(String cate_title) {
        this.cate_title = cate_title;
    }

    public String getCate_description() {
        return cate_description;
    }

    public void setCate_description(String cate_description) {
        this.cate_description = cate_description;
    }
}
