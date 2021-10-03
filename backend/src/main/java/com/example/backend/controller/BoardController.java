package com.example.backend.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import com.example.backend.model.Multimedia;
import com.example.backend.model.PostForCreate;
import com.example.backend.service.MultimediaService;
import com.example.backend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Post;
import com.example.backend.service.BoardService;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private MultimediaService multimediaService;

	@Autowired
	private S3Service s3Service;

	// get all board 
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;
		return ResponseEntity.ok(boardService.getPagingBoard(p_num));
	}

    @PostMapping("/board")
    public ResponseEntity<Post> createBoard(@RequestPart(value = "file", required=false) MultipartFile file, @RequestPart("body") PostForCreate post) throws IOException {
		MultipartFile multipartFile = file;
		String url = "";
		if (multipartFile != null) {
			url = s3Service.upload(multipartFile);
		}
		post.setImageUrl(url);
		Post after_post = boardService.createBoard(post);
		if (multipartFile != null) {
			multimediaService.createMultimedia(after_post.getId(), multipartFile.getContentType().toString(), url);
		}
		return ResponseEntity.ok(after_post);
    }

	@GetMapping("/board/{no}")
	public ResponseEntity<Post> getBoardByNo(
			@PathVariable Integer no) {
		return ResponseEntity.ok(boardService.getBoard(no));
	}

	@PutMapping("/board/{no}")
	public ResponseEntity<Post> updateBoardByNo(
			@PathVariable Integer no, @RequestBody Post post){
		return ResponseEntity.ok(boardService.updateBoard(no, post));
	}

	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
			@PathVariable Integer no) {
		
		return ResponseEntity.ok(boardService.deleteBoard(no));
	}
}