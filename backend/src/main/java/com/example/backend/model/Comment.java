package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "comments")
@DynamicInsert
@DynamicUpdate
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "contents")
    private String contents;

    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "post_id")
    private Integer postId;
}
