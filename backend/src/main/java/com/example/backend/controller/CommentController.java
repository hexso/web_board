package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/comment")
    public Comment addComment(@RequestBody Comment comment){
        return boardService.addComment(comment);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> updateCommentByNo(@PathVariable Integer id, @RequestBody Comment comment){
        return boardService.updateComment(id, comment);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCommentByNo(@PathVariable Integer id) {
        return boardService.deleteComment(id);
    }
}
