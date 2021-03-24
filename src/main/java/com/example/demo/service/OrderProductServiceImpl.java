package com.example.demo.service;

import com.example.demo.model.OrderProduct;
import com.example.demo.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public void save(OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }

    @Override
    public List<OrderProduct> findByUserOrderId(Long id) {
        return orderProductRepository.findByUserOrderId(id);
    }
}
