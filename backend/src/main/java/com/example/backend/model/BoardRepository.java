package com.example.backend.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
