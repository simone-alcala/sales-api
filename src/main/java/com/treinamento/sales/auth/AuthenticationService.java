package com.treinamento.sales.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.treinamento.sales.config.JwtService;
import com.treinamento.sales.entities.User;
import com.treinamento.sales.entities.enums.Role;
import com.treinamento.sales.repositories.UserRepository;
import com.treinamento.sales.services.exceptions.DatabaseException;

@Service
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder encoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserRepository repository, PasswordEncoder encoder, JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.repository = repository;
    this.encoder = encoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse signUp(RegisterRequest request) {

    if (repository.findByEmail(request.getEmail()).isPresent()) {
			throw new DatabaseException("Email already registered");
		}

    User user = new User(request);
    
    user.setPassword(encoder.encode(request.getPassword()));
    user.setRole(Role.USER);

    repository.save(user);

    String jwtToken = jwtService.generateToken(user);

    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse signIn(AuthenticationRequest request) {
    
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
          request.getEmail(), 
          request.getPassword()
        )
      );

      User user = repository
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

      String jwtToken = jwtService.generateToken(user);
      
      return new AuthenticationResponse(jwtToken);

  }
  

}
