package com.Grivence_Management_System.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Grivence_Management_System.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, String> {

	List<Complaint> findComplaintByStudentId(long id);

	Complaint findComplaintByStudentId(String id);

}
