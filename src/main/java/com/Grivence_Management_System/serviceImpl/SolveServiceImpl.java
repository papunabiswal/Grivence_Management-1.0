package com.Grivence_Management_System.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Grivence_Management_System.entity.Solve;
import com.Grivence_Management_System.repository.SolveRepository;
import com.Grivence_Management_System.service.SolveService;

@Service
public class SolveServiceImpl implements SolveService {
	
	@Autowired
	private SolveRepository solveRepo;

	@Override
	public void saveSolvedComplaints(Solve solve) {
		solveRepo.save(solve);
	}

	@Override
	public List<Solve> findAllSolveComplaints() {
		return solveRepo.findAll();
	}

	@Override
	public void deleteSolveComplaintById(String id) {
		solveRepo.deleteById(id);
		
	}

}
