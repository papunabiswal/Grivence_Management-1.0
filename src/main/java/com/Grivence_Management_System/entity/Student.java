package com.Grivence_Management_System.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "registration Number should not be empty")
	private String registrationNumber;
	
	@NotEmpty(message = "firstName should not be empty")
	private String firstName;
	
	@NotEmpty(message = "lastName should not be empty")
	private String lastName;
	
	private String semister;
	
	private String Branch;
	
	@NotEmpty(message = "email should not be empty")
	@Email
	private String email;
	
	@Min(10)
	private String mobile;
	
    @NotEmpty(message = "Password is mandatory")
    private String password;

    @NotEmpty(message = "Confirm Password is mandatory")
    private String confirmPassword;  
    
    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @Cascade(value = { CascadeType.REMOVE, CascadeType.SAVE_UPDATE })
	private Set<Complaint> complaints = new HashSet<>();

    @OneToMany(mappedBy = "student", orphanRemoval = true, cascade = CascadeType.PERSIST)
//    @Cascade(value = { CascadeType.REMOVE, CascadeType.SAVE_UPDATE })
	private Set<Solve> colplaintSolve = new HashSet<>();
    

}
