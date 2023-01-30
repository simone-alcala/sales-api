package com.treinamento.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinamento.sales.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
