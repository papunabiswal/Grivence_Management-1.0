package com.Grivence_Management_System.service;

import java.util.List;

import com.Grivence_Management_System.entity.Student;

import jakarta.validation.Valid;

public interface StudentService {

	Student findByEmailId(String email);

	void register(@Valid Student user);

	List<Student> getAllStudent();

	void deleteStudentById(long id);

	Student findStudentByRegistrationNumber(String registrationNumber);

	List<Student> findAllStudents();

	void updateStudent(@Valid Student student);

	List<Student> searchStudents(String query);

}
