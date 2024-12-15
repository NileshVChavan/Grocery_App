package com.grocery.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grocery.entity.GroceryItem;
import com.grocery.entity.Order;
import com.grocery.responsebean.GroceryItemsResponseBean;
import com.grocery.service.GroceryService;
import com.grocery.serviceImpl.GroceryServiceImpl;
import com.grocery.serviceImpl.OrderServiceImpl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private GroceryService groceryService;

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/grocery-items")
    public ResponseEntity<GroceryItemsResponseBean> getAvailableItems() {
    	log.info("entered into getAvailbleItems method of UserController class");
        return ResponseEntity.ok(groceryService.getAllGroceryItems());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
    	log.info("entered into placeOrder method of UserController class");
        return ResponseEntity.ok(orderService.createOrder(order));
    }
}
