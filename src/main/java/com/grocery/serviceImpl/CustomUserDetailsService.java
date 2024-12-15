package com.grocery.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grocery.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	log.info("entered into loadUserByUsername method of CustomUserDetailsService class");
        return userRepository.findByEmail(email).get();
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
