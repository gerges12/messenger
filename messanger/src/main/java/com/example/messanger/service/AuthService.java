package com.example.messanger.service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.messanger.dao.RoleRepository;
import com.example.messanger.dao.UserRepository;
import com.example.messanger.dao.VerificationTokenRepository;
import com.example.messanger.dto.AuthenticationResponse;
import com.example.messanger.dto.LoginRequest;
import com.example.messanger.dto.RegisterRequest;
import com.example.messanger.entity.Role;
import com.example.messanger.entity.User;
import com.example.messanger.entity.VerificationToken ;
import com.example.messanger.exceptions.EmailAlreadyExistException;
import com.example.messanger.security.JwtProvider;

import io.jsonwebtoken.security.InvalidKeyException;

@Service
public class AuthService {
	
	@Autowired
	RefreshTokenService refreshTokenService  ;
	
	@Autowired
     UserRepository  userrepository ;
	
	@Autowired
    JwtProvider  jwtProvider  ;
	
	@Autowired
	RoleRepository roleRepository  ;
	
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
    VerificationTokenRepository verificationTokenRepository ;
	
	@Autowired
    AuthenticationManager authenticationManager ;
    

	public  void signup(RegisterRequest registerRequest) {
		
		
		if (userrepository.findByEmail(registerRequest.getEmail()).isPresent() ) 
		{
		 throw new EmailAlreadyExistException("email " +registerRequest.getEmail() + " is already exist ") ;
		}
		
		else if (userrepository.findByUsername(registerRequest.getUsername()).isPresent()) 
		{
		 throw new EmailAlreadyExistException("username " +registerRequest.getUsername() + " is already exist ") ;
		}
		
		   

		 User user = new User();
	        user.setUsername(registerRequest.getUsername());
	        user.setEmail(registerRequest.getEmail());
	        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	        user.setCreated(new Date());
	        user.setRoles(null);
	        user.setDob(registerRequest.getDob());
	        user.setPhone(registerRequest.getPhone());
	        user.setSex(registerRequest.getSex());
	        user.setAge(registerRequest.getAge());

	        userrepository.save(user);
	        
	        
	        
	        
	      //  String token = generateVerificationToken(user);
		
		
	}

	private  String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        

        verificationTokenRepository.save(verificationToken);
        return token;
	}
	
	
	
	public AuthenticationResponse login(LoginRequest  loginRequest) {
		Authentication authenticate = authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
		
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();

		
		
		
	}

	 @Transactional(readOnly = true)
	    public User getCurrentUser() {
	        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
	                getContext().getAuthentication().getPrincipal();
	        return userrepository.findByUsername(principal.getUsername())
	                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
	    }
	
	
	

}
