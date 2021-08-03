package com.example.backend.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.backend.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    public final static String SELECT_BOARD_LIST_PAGED = ""
			+ "SELECT "
			+ "id,"
			+ "type,"
			+ "title,"
			+ "contents,"
			+ "author_id,"
			+ "created_time,"
			+ "updated_time,"
			+ "likes,"
			+ "readcnt"
			+ " FROM posts WHERE 0 < id "
			+ "ORDER BY id DESC LIMIT ?1, ?2";

    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
	List<Board> findFromTo(
			final Integer objectStartNum,
			final Integer objectEndNum);
}
