package com.Grivence_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grivence_Management_System.entity.Solve;
import com.Grivence_Management_System.service.SolveService;

@Controller
public class SolveController {
	
	@Autowired
	private SolveService solveService;
	
	@RequestMapping("/allSolveComplaints")
	public String allSolveComplaints(Model model) {
		List<Solve> solveComplaints = solveService.findAllSolveComplaints();
		model.addAttribute("solveComplaints", solveComplaints);
		return "admin/all_solve_complaints";
	}
	
	@RequestMapping("/deletesolvecomplaint/{id}")
	public String deleteSolveComplaintById(@PathVariable("id") String id) {
		solveService.deleteSolveComplaintById(id);
		return "admin/all_solve_complaints";
	}

}
