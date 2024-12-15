package com.grocery.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.grocery.dto.JwtResponse;
import com.grocery.dto.LoginRequest;
import com.grocery.dto.RegisterRequest;
import com.grocery.entity.User;
import com.grocery.repository.UserRepository;
import com.grocery.service.AuthService;
import com.grocery.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtTokenUtil;

    public String register(RegisterRequest request) {
    	log.info("entered into register method of AuthServiceImpl class");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole().toUpperCase());
        userRepository.save(user);
        return "User registered successfully!";
    }

    public  JwtResponse login(LoginRequest request)  {
    	log.info("entered into login method of AuthServiceImpl class");
       User user = (userRepository.findByEmail(request.getEmail())).get();
       if(user ==null) {
         throw new UsernameNotFoundException("User not found");
       }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UnknownError("Invalid credentials");
        }
        String token = jwtTokenUtil.generateToken(user);
        return new JwtResponse(token);
    }

}
