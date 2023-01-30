package com.treinamento.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinamento.sales.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
