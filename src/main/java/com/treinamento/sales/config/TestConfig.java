package com.treinamento.sales.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.treinamento.sales.entities.User;
import com.treinamento.sales.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "User 1", "user.1@gmail.com", "19987654321", "topSecret");
		User u2 = new User(null, "User 2", "user.2@gmail.com", "19123456789", "secret");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}
