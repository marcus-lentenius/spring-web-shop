package com.example.demo.service;

import com.example.demo.model.Cart;

import java.util.List;

public interface CartService {
    void save(Cart cart);

    Cart findByUserIdAndProductId(Long userId, Long productId);

    List<Cart> findAllByUserId(Long id);

    void delete(Cart cart);
}
