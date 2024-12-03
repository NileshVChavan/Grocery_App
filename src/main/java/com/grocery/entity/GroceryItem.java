package com.grocery.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private int quantity;
}
