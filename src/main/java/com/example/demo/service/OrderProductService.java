package com.example.demo.service;

import com.example.demo.model.OrderProduct;

import java.util.List;

public interface OrderProductService {
    void save(OrderProduct orderProduct);

    List<OrderProduct> findByUserOrderId(Long id);


}
