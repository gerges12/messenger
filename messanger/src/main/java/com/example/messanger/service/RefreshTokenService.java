package com.example.messanger.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import  com.example.messanger.dao.RefreshTokenRepository;
import  com.example.messanger.entity.RefreshToken;
import  com.example.messanger.exceptions.MassangereException;

import lombok.AllArgsConstructor;

@Service
@Transactional
public class RefreshTokenService {

	@Autowired
    RefreshTokenRepository refreshTokenRepository  ;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new MassangereException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
            refreshTokenRepository.deleteByToken(token);
    }
}
