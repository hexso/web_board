package com.example.backend.service;

import java.util.List;

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
}