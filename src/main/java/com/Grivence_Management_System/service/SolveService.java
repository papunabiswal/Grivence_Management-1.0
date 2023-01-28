package com.Grivence_Management_System.service;

import java.util.List;

import com.Grivence_Management_System.entity.Solve;

public interface SolveService {

	void saveSolvedComplaints(Solve solve);

	List<Solve> findAllSolveComplaints();

	void deleteSolveComplaintById(String id);

}
