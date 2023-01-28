package com.Grivence_Management_System.service;

import java.util.List;

import com.Grivence_Management_System.entity.Complaint;

import jakarta.validation.Valid;

public interface ComplaintService {

	void complaintsave(String registrationNumber, @Valid Complaint complaint);

	List<Complaint> findAllComplaints();

	List<Complaint> findComplaintsByStudentId(long id);

	Complaint findComplaintsByStudentId(String id);

	Complaint findComplaintByComplaintId(String id);

	void deleteComplaintById(String id);

}
