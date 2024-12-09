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

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private GroceryService groceryService;

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/grocery-items")
    public ResponseEntity<GroceryItemsResponseBean> getAvailableItems() {
        return ResponseEntity.ok(groceryService.getAllGroceryItems());
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }
}
