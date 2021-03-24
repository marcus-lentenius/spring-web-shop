package com.example.demo.repository;

import com.example.demo.model.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends CrudRepository<UserOrder, Long> {
    List<UserOrder> findAllByUserId(Long id);
}
