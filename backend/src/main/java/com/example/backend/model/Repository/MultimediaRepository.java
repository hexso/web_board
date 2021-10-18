package com.example.backend.model.Repository;

import com.example.backend.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {
}
