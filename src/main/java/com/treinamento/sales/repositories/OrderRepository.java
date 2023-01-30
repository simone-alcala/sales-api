package com.treinamento.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinamento.sales.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
