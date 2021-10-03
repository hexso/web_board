package com.example.backend.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "multimedia")
@DynamicInsert
@DynamicUpdate
public class Multimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    public Multimedia(){}

    public Multimedia(Integer postId, String type, String url){
        this.postId = postId;
        this.type = type;
        this.url = url;
    }
}
