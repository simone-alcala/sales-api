package com.treinamento.sales.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treinamento.sales.dto.UserDTO;
import com.treinamento.sales.utils.ToUpperCaseTrim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String phone;
	
	@Column(nullable = false)
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = ToUpperCaseTrim.setText(name);
		this.email = ToUpperCaseTrim.setText(email);
		this.phone = ToUpperCaseTrim.setText(phone);
		this.password = password;
	}
	
	public User(UserDTO userDTO) {
		this.name = ToUpperCaseTrim.setText(userDTO.name());
		this.email = ToUpperCaseTrim.setText(userDTO.email());
		this.phone = ToUpperCaseTrim.setText(userDTO.phone());
		this.password = userDTO.password();
	  }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = ToUpperCaseTrim.setText(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = ToUpperCaseTrim.setText(email);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = ToUpperCaseTrim.setText(phone);
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
}

