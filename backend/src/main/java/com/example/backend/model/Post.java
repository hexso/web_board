package com.example.backend.model;

import java.util.*;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "posts")
@DynamicInsert
@DynamicUpdate
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany
	@JoinColumn(name = "post_id")
	Set<Multimedia> multimediaSet = new HashSet<>();

	@OneToMany
	@OrderBy(value = "updated_time DESC")
	@JoinColumn(name = "post_id")
	List<Comment> commentSet = new ArrayList<>();

	@Column(name = "title")
	private String title;
	
	@Column(name = "contents")
	private String contents;
		
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	@Column(name = "likes")
	private Integer likes;

	@Column(name = "readcnt")
	private Integer readcnt;

	@Column(name = "type")
	private String postType;

	@Column(name = "top_image_url")
	private String topImageUrl;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private User user;

// ---Getter/Setter ---
}
