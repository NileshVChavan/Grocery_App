package com.grocery.service;

import org.springframework.stereotype.Service;

import com.grocery.dto.JwtResponse;
import com.grocery.dto.LoginRequest;
import com.grocery.dto.RegisterRequest;

public interface AuthService {
	  public String register(RegisterRequest request);
	  public  JwtResponse login(LoginRequest request);
}
