package com.example.backend.service;

import com.example.backend.model.Multimedia;
import com.example.backend.model.MultimediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultimediaService {

    @Autowired
    private MultimediaRepository multimediaRepository;

    public Multimedia createMultimedia(Multimedia multimedia){
        return multimediaRepository.save(multimedia);
    }
}