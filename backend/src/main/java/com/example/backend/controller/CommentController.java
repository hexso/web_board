package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/comment")
    public Comment addComment(@RequestBody Comment comment){
        return boardService.addComment(comment);
    }
}
