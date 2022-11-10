package com.tong.rmit.blogapis.payloads;

import com.tong.rmit.blogapis.repositories.CategoryRepo;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

    private int cate_id;
    @NotEmpty
    private String cate_title;

    @NotEmpty
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
