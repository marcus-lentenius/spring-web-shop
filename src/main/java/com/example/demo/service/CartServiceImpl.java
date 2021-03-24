package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart findByUserIdAndProductId(Long userId, Long productId) {
        return cartRepository.findByUserIdAndProductId(userId, productId);
    }
    @Override
    public List<Cart> findAllByUserId(Long id) {
        return cartRepository.findAllByUserId(id);
    }

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }
}
