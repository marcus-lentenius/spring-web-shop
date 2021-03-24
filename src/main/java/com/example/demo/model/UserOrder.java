package com.example.demo.model;

import lombok.Data;
import org.hibernate.mapping.ToOne;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class UserOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String date;
    private Long userId;
    private boolean isShipped;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userOrder")
    private List<OrderProduct> orderProduct;
//
//    @ManyToOne
//    private User user;
}
