package com.example.backend.model.Repository;

import java.util.List;

import com.example.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {
    public final static String SELECT_BOARD_LIST_PAGED = ""
			+ "SELECT "
			+ "id,"
			+ "type,"
			+ "title,"
			+ "contents,"
			+ "author_id,"
			+ "created_time,"
			+ "updated_time,"
			+ "top_image_url,"
			+ "likes,"
			+ "readcnt"
			+ " FROM posts WHERE 0 < id "
			+ "ORDER BY id DESC LIMIT ?1, ?2";

    @Query(value = SELECT_BOARD_LIST_PAGED, nativeQuery = true)
	List<Post> findFromTo(
			final Integer objectStartNum,
			final Integer objectEndNum);
}
