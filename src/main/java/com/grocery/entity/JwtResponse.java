package com.grocery.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

public class JwtResponse {

    private String token;
    private String type = "Bearer";  // Typically "Bearer" for JWT tokens
    private String username;
   
    private String role; // User roles (optional)

	public JwtResponse(String token, String string, String string2) {
		super();
		this.token = token;
		this.type = type;
		this.username = username;
		this.role = role;
	}


    
 

 
   }
