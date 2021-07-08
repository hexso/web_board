package com.example.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}