package com.Grivence_Management_System.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name="complaints")
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@Lob
	@NotEmpty(message="complaint can not be empty")
	private String complaint;
	
	@CreationTimestamp
	private LocalDateTime createdOn;
	
	@ManyToOne
	(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name="student_id", nullable= false)
	private Student student;

	
	
	

}
