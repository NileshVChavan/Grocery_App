package com.grocery.service;

import java.util.List;

import com.grocery.entity.GroceryItem;
import com.grocery.responsebean.GroceryItemResponseBean;
import com.grocery.responsebean.GroceryItemsResponseBean;

public interface GroceryService {
	   public GroceryItemResponseBean addGroceryItem(List<GroceryItem> item);
	   public GroceryItemsResponseBean getAllGroceryItems() ;
	   public GroceryItemResponseBean deleteGroceryItem(Long id);
	   public GroceryItem updateGroceryItem(Long id, GroceryItem item);
}
