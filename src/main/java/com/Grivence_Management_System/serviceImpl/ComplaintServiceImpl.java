package com.Grivence_Management_System.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grivence_Management_System.entity.Complaint;
import com.Grivence_Management_System.entity.Student;
import com.Grivence_Management_System.repository.ComplaintRepository;
import com.Grivence_Management_System.repository.StudentRepository;
import com.Grivence_Management_System.service.ComplaintService;

import jakarta.validation.Valid;

@Service
public class ComplaintServiceImpl implements ComplaintService {
	
	@Autowired
	private ComplaintRepository complaintRepo;
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public void complaintsave(String registrationNumber, @Valid Complaint complaint) {
		Student student = studentRepo.findStudentByRegistrationNumber(registrationNumber);
		complaint.setStudent(student);
		complaintRepo.save(complaint);
	}

	@Override
	public List<Complaint> findAllComplaints() {
		return complaintRepo.findAll();
	}

	@Override
	public List<Complaint> findComplaintsByStudentId(long id) {
		
		return complaintRepo.findComplaintByStudentId(id);
	}

	@Override
	public Complaint findComplaintsByStudentId(String id) {
		return complaintRepo.findComplaintByStudentId(id);
	}

	@Override
	public Complaint findComplaintByComplaintId(String id) {
		return complaintRepo.findById(id).get();
	}

	@Override
	public void deleteComplaintById(String id) {
		complaintRepo.deleteById(id);
	}
	
	

}
