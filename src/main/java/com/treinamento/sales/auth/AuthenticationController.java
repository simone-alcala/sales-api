package com.treinamento.sales.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {
  
  private final AuthenticationService service;

  public AuthenticationController(AuthenticationService service) {
    this.service = service;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<AuthenticationResponse> signUp(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.signUp(request));
  }

  @PostMapping("/sign-in")
  public ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.signIn(request));
  }

}
