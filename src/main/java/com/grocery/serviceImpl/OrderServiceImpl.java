package com.grocery.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;

import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private GroceryItemRepository groceryItemRepository;

	public Order createOrder(Order order) {
		log.info("entered into createOrder method of OrderServiceImpl class");
		int count = order.getItems().listIterator().next().getQuantity();
		for (OrderItem m : order.getItems()) {
//    		int count=m.getQuantity(); 
			if (groceryItemRepository.findById(m.getGroceryItemId()) != null) {
				Long count1 = groceryItemRepository.findQuantityById(m.getGroceryItemId());
				count1 = count1 - count;

				groceryItemRepository.saveQuentity(count1, m.getGroceryItemId());

			}

		}
		return orderRepository.save(order);

	}
}
