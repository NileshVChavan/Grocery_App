package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grocery.entity.GroceryItem;

import jakarta.transaction.Transactional;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
	@Query("SELECT g.quantity FROM GroceryItem g WHERE g.id = :groceryItemId")
	Long findQuantityById(@Param("groceryItemId") Long groceryItemId);

	@Modifying
	@Transactional
	@Query("UPDATE GroceryItem g SET g.quantity = :quentity WHERE g.id = :groceryItemId")
	void saveQuentity(@Param("quentity") Long quentity, @Param("groceryItemId") Long groceryItemId);
}