package com.example.codingShuttle.SecurityApp.Security_Application.repositories;


import com.example.codingShuttle.SecurityApp.Security_Application.entities.Session;
import com.example.codingShuttle.SecurityApp.Security_Application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface SessionRepository extends JpaRepository<Session,Long> {

    List<Session> findByUser(UserEntity user);

   Optional<Session> findByRefreshToken(String refreshToken);
}
