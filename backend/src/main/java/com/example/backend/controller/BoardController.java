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
		
		return boardService.getPagingBoard(p_num);
	}

    @PostMapping("/board")
    public Post createBoard(@RequestPart(value = "file", required=false) MultipartFile file, @RequestPart("body") PostForCreate post) throws IOException {
		MultipartFile multipartFile = file;
		String url = "";
		if (multipartFile != null) {
			String fileName = multipartFile.getOriginalFilename();
			Date time = new Date();
			String nowTime = time.toString();
			fileName += nowTime;

			url = s3Service.upload(multipartFile, fileName);
		}
		post.setImageUrl(url);
		Post after_post = boardService.createBoard(post);
		if (multipartFile != null) {
			Multimedia multimedia = new Multimedia();
			multimedia.setPostId(after_post.getId());
			multimedia.setType(multipartFile.getContentType().toString());
			multimedia.setUrl(url);
			multimediaService.createMultimedia(multimedia);
		}
		return after_post;
    }

	@GetMapping("/board/{no}")
	public ResponseEntity<Post> getBoardByNo(
			@PathVariable Integer no) {
		
		return boardService.getBoard(no);
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