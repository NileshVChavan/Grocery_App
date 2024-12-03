package com.grocery.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entity.GroceryItem;
import com.grocery.repository.GroceryItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroceryService {

    @Autowired
    private GroceryItemRepository repository;

    public List<GroceryItem> addGroceryItem(List<GroceryItem> item) {
    	List<GroceryItem> itemm = new  ArrayList<>();
    	for(GroceryItem a:item) {
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
