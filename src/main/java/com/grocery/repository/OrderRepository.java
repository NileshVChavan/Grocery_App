package com.grocery.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {}
