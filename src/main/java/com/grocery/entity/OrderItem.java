package com.grocery.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long groceryItemId;
    private int quantity;
	

}
