package com.grocery.serviceImpl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entity.GroceryItem;
import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;
import com.grocery.entity.User;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;
    
    public Order createOrder(Order order) {
    	int count=order.getItems().listIterator().next().getQuantity();
    	for(OrderItem m:order.getItems()) {
    	if(groceryItemRepository.findById(m.getGroceryItemId()) != null) {
    		Long count1=groceryItemRepository.findQuantityById(m.getGroceryItemId());
    		count1=count1-count;
    		
    		groceryItemRepository.saveQuentity(count1,m.getGroceryItemId());
    		
    	}
    	 
    	}
    	 return orderRepository.save(order);
			
    }
}
