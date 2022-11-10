package com.tong.rmit.blogapis.models;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cate_id;

    @Column(name ="title", length = 100, nullable = false)
    private String cate_title;

    @Column(name="description")
    private String cate_description;

    public Category(){

    }

    public String getCate_description() {
        return cate_description;
    }

    public void setCate_description(String cate_description) {
        this.cate_description = cate_description;
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
}
