package com.treinamento.sales.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.treinamento.sales.entities.Order;
import com.treinamento.sales.entities.User;
import com.treinamento.sales.repositories.OrderRepository;
import com.treinamento.sales.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "User One", "user.1@gmail.com", "19987654321", "topSecret");
		User u2 = new User(null, "User Two", "user.2@gmail.com", "19123456789", "secret");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
		
		Order o1 = new Order(null, Instant.parse("2023-01-26T11:59:00Z"), u1);
		Order o2 = new Order(null, Instant.parse("2023-01-27T12:58:01Z"), u2);
		Order o3 = new Order(null, Instant.parse("2023-01-30T13:57:02Z"), u1);
		
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		
		
	}
}
