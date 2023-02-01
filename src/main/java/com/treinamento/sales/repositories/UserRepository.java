package com.treinamento.sales.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.treinamento.sales.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);
}
