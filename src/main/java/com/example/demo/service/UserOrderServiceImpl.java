package com.example.demo.service;

import com.example.demo.model.UserOrder;
import com.example.demo.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userOrderRepository;

    @Override
    public void save(UserOrder userOrder) {
        userOrderRepository.save(userOrder);
    }

    @Override
    public List<UserOrder> findByUserId(Long id) {
        return userOrderRepository.findAllByUserId(id);
    }

    @Override
    public UserOrder findById(Long id) {
        return userOrderRepository.findById(id).get();
    }
}
