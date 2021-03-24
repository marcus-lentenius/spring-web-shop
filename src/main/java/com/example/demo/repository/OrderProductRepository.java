package com.example.demo.repository;

import com.example.demo.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
    List<OrderProduct> findByUserOrderId(Long id);
}
