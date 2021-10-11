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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User user;

    @Column(name = "post_id")
    private Integer postId;
}
