package com.Grivence_Management_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Grivence_Management_System.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByEmail(String email);

	Student findStudentByRegistrationNumber(String registrationNumber);

	@Query("SELECT s from Student s WHERE " +
		   " s.registrationNumber LIKE CONCAT ('%', :query, '%') OR " +
		   " s.email LIKE CONCAT ('%', :query, '%')")
	List<Student> searchStudents(@Param("query") String query);

}
