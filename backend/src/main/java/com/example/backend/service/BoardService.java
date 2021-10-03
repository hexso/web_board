package com.example.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.backend.dto.UserPublic;
import com.example.backend.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.util.PagingUtil;

@Service
public class BoardService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	public int findAllCount() {
		return (int) postRepository.count();
	}

	// get paging boards data
	public ResponseEntity<Map> getPagingBoard(Integer p_num) {
		Map result = null;
		
		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		List<Post> list = postRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		pu.setObjectCountTotal(findAllCount());
		pu.setCalcForPaging();
		
		System.out.println("p_num : "+p_num);
		System.out.println(pu.toString());
		
		if (list == null || list.size() == 0) {
			return null;
		}
		
		result = new HashMap<>();
		result.put("pagingData", pu);
		result.put("list", list);
		
		return ResponseEntity.ok(result);
	}

	// get boards data
	public List<Post> getAllBoard() {
		return postRepository.findAll();
	}

    public Post createBoard(PostForCreate post) {
		User user = userRepository.getOne(post.getAuthorId());
		System.out.println(user.getNickName());
		Post realPost = new Post();
		realPost.setPostType(post.getPostType());
		realPost.setContents(post.getContents());
		realPost.setTitle(post.getTitle());
		realPost.setUser(user);
		realPost.setTopImageUrl(post.getImageUrl());
		return postRepository.save(realPost);
    }

	public ResponseEntity<Post> getBoard(Integer id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		return ResponseEntity.ok(post);
	}

	public ResponseEntity<Post> updateBoard(
			Integer id, Post updatedPost) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		post.setPostType(updatedPost.getPostType());
		post.setTitle(updatedPost.getTitle());
		post.setContents(updatedPost.getContents());
		post.setUpdatedTime(new Date());
		
		Post endUpdatedPost = postRepository.save(post);
		return ResponseEntity.ok(endUpdatedPost);
	}

	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		
		postRepository.delete(post);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+id+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	public Comment addComment(Comment comment){
		return commentRepository.save(comment);
	}

	public ResponseEntity<Comment> updateComment(Integer id,Comment comment) {
		comment.setId(id);
		Comment com = commentRepository.save(comment);
		return ResponseEntity.ok(com);
	}

	public ResponseEntity<Map<String, Boolean>> deleteComment(Integer commentId){
		commentRepository.deleteById(commentId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Comment Data by id : ["+commentId+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}