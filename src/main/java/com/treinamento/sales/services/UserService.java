package com.treinamento.sales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.treinamento.sales.dto.UserDTO;
import com.treinamento.sales.entities.User;
import com.treinamento.sales.repositories.UserRepository;
import com.treinamento.sales.services.exceptions.DatabaseException;
import com.treinamento.sales.services.exceptions.ResourceNotFoundException;
import com.treinamento.sales.utils.PasswordEncrypt;
import com.treinamento.sales.utils.ToUpperCaseTrim;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	
	public User insert(UserDTO userDTO) {
		
		if (repository.findByEmail(ToUpperCaseTrim.setText(userDTO.email())).isPresent()) {
			throw new DatabaseException("Email already registered");
		}

		User user = new User(userDTO);
		PasswordEncoder encoder = new PasswordEncrypt().getPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return repository.save(user);
		
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User user) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, user);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());		
	}
	
		
}
