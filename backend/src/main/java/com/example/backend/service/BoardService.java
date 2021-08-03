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
import com.example.backend.util.PagingUtil;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public int findAllCount() {
		return (int) boardRepository.count();
	}

	// get paging boards data
	public ResponseEntity<Map> getPagingBoard(Integer p_num) {
		Map result = null;
		
		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
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
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

	public ResponseEntity<Board> getBoard(Integer id) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		return ResponseEntity.ok(board);
	}

	public ResponseEntity<Board> updateBoard(
			Integer id, Board updatedBoard) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}

	public ResponseEntity<Map<String, Boolean>> deleteBoard(
			Integer id) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Not exist Board Data by no : ["+id+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+id+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}