package com.grocery.serviceImpl;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;


import com.grocery.entity.GroceryItem;
import com.grocery.entity.User;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.UserRepository;
import com.grocery.responsebean.GroceryItemResponseBean;
import com.grocery.responsebean.GroceryItemsResponseBean;
import com.grocery.service.GroceryService;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class GroceryServiceImpl implements GroceryService {

    @Autowired
    private GroceryItemRepository repository;
    
    @Autowired
    private CustomUserDetailsService custom;
    
    @Autowired
    private UserRepository userRepository;

    public GroceryItemResponseBean addGroceryItem(List<GroceryItem> item) {
    	GroceryItemResponseBean groceryItemResponseBean=new GroceryItemResponseBean();
    	 Optional<User> user1 = null;
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         if (principal instanceof UserDetails) {
             UserDetails userDetails = (UserDetails) principal;
             user1=userRepository.findByUsername(userDetails.getUsername());
             
         }     	List<GroceryItem> itemm = new  ArrayList<>();
    	for(GroceryItem groceryitem:item) {
    		
    		groceryitem.setUser(user1.get());
    		GroceryItem grocery=  repository.save(groceryitem);
    		if(grocery!=null) {
    	 groceryItemResponseBean.setMsg("successfully added grocery items");
    	 groceryItemResponseBean.setStatus(true);
    		}
    		else{
    			 groceryItemResponseBean.setMsg("grocery item not saved");
    	    	 groceryItemResponseBean.setStatus(false);
    		}
    	}
		return groceryItemResponseBean;
       
    }

    public GroceryItemsResponseBean getAllGroceryItems() {
    	GroceryItemsResponseBean bean=new GroceryItemsResponseBean();
        List<GroceryItem> item= repository.findAll();
        if(item!=null) {
        	bean.setMsg("successfully retrived grocery items details");
        	bean.setStatus(true);
        	bean.setGroceryItem(item);
        }else {
        	bean.setMsg("not found grocery item");
        	bean.setStatus(false);
        }
        return bean;
    }

    public GroceryItemResponseBean deleteGroceryItem(Long id) {
    	GroceryItemResponseBean bean=new GroceryItemResponseBean();
    	if(repository.findById(id).equals(id)) {
        repository.deleteById(id);
    	bean.setMsg("successfully deleted grocery item");
    	bean.setStatus(true);
    	}
    	else {
    		bean.setMsg("wrong id");
        	bean.setStatus(false);
    	}
        return bean;
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
