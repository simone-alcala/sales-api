package com.treinamento.sales.auth;

import com.treinamento.sales.utils.ToUpperCaseTrim;

public class AuthenticationRequest {
  
  private String email;
  private String password;
  
  public AuthenticationRequest() {
  }

  public AuthenticationRequest(String email, String password) {
    this.email = ToUpperCaseTrim.setText(email);
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email =  ToUpperCaseTrim.setText(email);
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  
}
