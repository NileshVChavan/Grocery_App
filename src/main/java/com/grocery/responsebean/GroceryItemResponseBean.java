package com.grocery.responsebean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.grocery.entity.GroceryItem;

import lombok.Data;

@Component
@Data
public class GroceryItemResponseBean {
  String msg;
  boolean status;

	
	
}