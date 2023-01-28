package com.Grivence_Management_System.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message="firstName should not be empty")
	private String firstName;
	
	@NotBlank(message="lastName should not be empty")
	private String  lastName;
	
	@NotBlank(message=" email should not be empty")
	@Email
	private String email;
	
	private String hod;
	
	@NotBlank(message="password should not be empty")
	private String password;
	
	@NotBlank(message="confirmPassword should not be empty")
	private String confirmPassword;

}
