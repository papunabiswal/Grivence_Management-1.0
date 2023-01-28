package com.Grivence_Management_System.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grivence_Management_System.entity.Student;
import com.Grivence_Management_System.repository.StudentRepository;
import com.Grivence_Management_System.service.StudentService;

import jakarta.validation.Valid;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student findByEmailId(String email) {
		Student student = studentRepo.findByEmail(email);
		return student;
	}

	@Override
	public void register(@Valid Student user) {
		studentRepo.save(user);
	}

	@Override
	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Override
	public void deleteStudentById(long id) {
		studentRepo.deleteById(id);
	}


	@Override
	public Student findStudentByRegistrationNumber(String registrationNumber) {
		Student existingStudent = studentRepo.findStudentByRegistrationNumber(registrationNumber);
		return existingStudent;
	}

	@Override
	public List<Student> findAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public void updateStudent(@Valid Student student) {
		studentRepo.save(student);
	}

	@Override
	public List<Student> searchStudents(String query) {
		return studentRepo.searchStudents(query);
	}

}
