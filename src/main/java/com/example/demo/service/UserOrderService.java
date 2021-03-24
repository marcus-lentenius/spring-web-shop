package com.example.demo.service;

import com.example.demo.model.UserOrder;

import java.util.List;

public interface UserOrderService {
    void save(UserOrder userOrder);

    List<UserOrder> findByUserId(Long id);

    UserOrder findById(Long id);
}
