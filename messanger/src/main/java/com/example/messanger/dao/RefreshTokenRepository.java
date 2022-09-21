package com.example.messanger.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.messanger.entity.RefreshToken;



public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
