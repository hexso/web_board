package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForCreate {

    private String title;

    private String contents;

    private String postType;

    private Integer authorId;

    private String imageUrl;
}
