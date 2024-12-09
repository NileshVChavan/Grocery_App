package com.grocery.responsebean;

import java.util.List;

import com.grocery.entity.GroceryItem;

import lombok.Data;

@Data
public class GroceryItemsResponseBean {
   String msg;
   boolean status;
   List<GroceryItem> groceryItem;
	
}
