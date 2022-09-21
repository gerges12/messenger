package com.example.messanger.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class AuthenticationResponse {

	private String authenticationToken;
    private String refreshToken;
    private Instant expiresAt;
    private String username;
}
