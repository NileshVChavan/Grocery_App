package com.grocery.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.grocery.entity.GroceryItem;
import com.grocery.responsebean.GroceryItemResponseBean;
import com.grocery.responsebean.GroceryItemsResponseBean;
import com.grocery.service.GroceryService;
import com.grocery.serviceImpl.GroceryServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private GroceryService groceryService;

    @PostMapping("/grocery-items")
//   @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GroceryItemResponseBean> addItem(@RequestBody List<GroceryItem> item) {
    	log.info("entered into AddItem method of AdminController class");
        return ResponseEntity.ok(groceryService.addGroceryItem(item));
    }

    @GetMapping("/grocery-items")
    public ResponseEntity<GroceryItemsResponseBean> getAllItems() {
    	log.info("entered into getAllItems method of AdminController class");
        return ResponseEntity.ok(groceryService.getAllGroceryItems());
    }

    @DeleteMapping("/grocery-items/{id}")
    public ResponseEntity<GroceryItemResponseBean> deleteItem(@PathVariable Long id) {
    	log.info("entered into deleteItem method of AdminController class");
        groceryService.deleteGroceryItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/grocery-items/{id}")
    public ResponseEntity<GroceryItem> updateItem(@PathVariable Long id, @RequestBody GroceryItem item) {
    	log.info("entered into UpdateItem method of AdminController class");
        return ResponseEntity.ok(groceryService.updateGroceryItem(id, item));
    }
}
