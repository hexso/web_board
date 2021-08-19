package com.example.backend.model;

import lombok.Getter;

@Getter
public class PostForCreate {

    private String title;

    private String contents;

    private String postType;

    private Integer authorId;
}
