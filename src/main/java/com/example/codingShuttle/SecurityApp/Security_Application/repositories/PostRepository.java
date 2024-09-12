package com.example.codingShuttle.SecurityApp.Security_Application.repositories;

import com.example.codingShuttle.SecurityApp.Security_Application.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
