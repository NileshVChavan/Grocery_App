package com.grocery.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;


import com.grocery.entity.GroceryItem;
import com.grocery.entity.User;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.UserRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.grocery.service.CustomUserDetailsService;
@Service
public class GroceryService {

    @Autowired
    private GroceryItemRepository repository;
    
    @Autowired
    private CustomUserDetailsService custom;
    
    @Autowired
    private UserRepository userRepository;

    public List<GroceryItem> addGroceryItem(List<GroceryItem> item) {
    	 Optional<User> user1 = null;
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (principal instanceof UserDetails) {
             UserDetails userDetails = (UserDetails) principal;
             user1=userRepository.findByUsername(userDetails.getUsername());
             
         }     	List<GroceryItem> itemm = new  ArrayList<>();
    	for(GroceryItem a:item) {
          a.setUser(user1.get());
    		itemm.add(a);
    	
    	}
    repository.saveAll(itemm);
		return itemm;
       
    }

    public List<GroceryItem> getAllGroceryItems() {
        return repository.findAll();
    }

    public void deleteGroceryItem(Long id) {
        repository.deleteById(id);
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem item) {
        return repository.findById(id).map(existing -> {
            existing.setName(item.getName());
            existing.setPrice(item.getPrice());
            existing.setQuantity(item.getQuantity());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
