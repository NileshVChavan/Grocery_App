package com.grocery.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {}
