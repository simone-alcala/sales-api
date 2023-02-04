package com.treinamento.sales.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treinamento.sales.auth.RegisterRequest;
import com.treinamento.sales.dto.UserDTO;
import com.treinamento.sales.entities.enums.Role;
import com.treinamento.sales.utils.ToUpperCaseTrim;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {
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

	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password, Role role) {
		super();
		this.id = id;
		this.name = ToUpperCaseTrim.setText(name);
		this.email = ToUpperCaseTrim.setText(email);
		this.phone = ToUpperCaseTrim.setText(phone);
		this.role = role;
		this.password = password;
	}
	
	public User(UserDTO userDTO) {
		this.name = ToUpperCaseTrim.setText(userDTO.name());
		this.email = ToUpperCaseTrim.setText(userDTO.email());
		this.phone = ToUpperCaseTrim.setText(userDTO.phone());
		this.password = userDTO.password();
	}

	public User(RegisterRequest register) {
		this.name = ToUpperCaseTrim.setText(register.getName());
		this.email = ToUpperCaseTrim.setText(register.getEmail());
		this.phone = ToUpperCaseTrim.setText(register.getPhone());
		this.password = register.getPassword();
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {	
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}

