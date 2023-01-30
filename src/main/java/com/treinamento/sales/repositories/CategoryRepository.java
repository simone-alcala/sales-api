package com.treinamento.sales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinamento.sales.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
