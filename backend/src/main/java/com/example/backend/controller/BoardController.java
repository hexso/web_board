package com.example.backend.controller;

import java.util.Map;

import com.example.backend.model.PostForCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Post;
import com.example.backend.service.BoardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;


	// get all board 
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;
		
		return boardService.getPagingBoard(p_num);
	}

    @PostMapping("/board")
    public Post createBoard(@RequestBody PostForCreate post){
		return boardService.createBoard(post);
    }

	@GetMapping("/board/{no}")
	public ResponseEntity<Post> getBoardByNo(
			@PathVariable Integer id) {
		
		return boardService.getBoard(id);
	}

	@PutMapping("/board/{no}")
	public ResponseEntity<Post> updateBoardByNo(
			@PathVariable Integer id, @RequestBody Post post){
		
		return boardService.updateBoard(id, post);
	}

	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer id) {
		
		return boardService.deleteBoard(id);
	}

}