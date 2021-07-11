package com.example.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.backend.model.Board;
import com.example.backend.model.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	

	// get boards data
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}

	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}