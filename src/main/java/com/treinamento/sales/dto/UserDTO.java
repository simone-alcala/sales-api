package com.treinamento.sales.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO (
		@NotBlank
		String name,
		
		@Email
		@NotNull
		String email,
		
		@Pattern(regexp = "^([0-9]+)$", message="Invalid phone number")
		String phone,
		
		@Size(min = 4, max = 10)
		String password
	) { 
	
}
