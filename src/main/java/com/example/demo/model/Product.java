package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String producer;
    private String category;
    private int price;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
//    private OrderProduct orderProduct;
//
//    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
//    private List<Cart> cart;
}

