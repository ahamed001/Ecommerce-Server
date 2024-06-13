package com.dsimilar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsimilar.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
