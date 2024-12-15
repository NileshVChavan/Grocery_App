package com.grocery.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

public class JwtResponse {

    private String token;
    private String type = "Bearer";  
    private String username;
   
    private String role; 

	public JwtResponse(String token, String string, String string2) {
		super();
		this.token = token;
		this.type = type;
		this.username = username;
		this.role = role;
	}


    
 

 
   }
