package com.Grivence_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grivence_Management_System.entity.Complaint;
import com.Grivence_Management_System.entity.Solve;
import com.Grivence_Management_System.entity.Student;
import com.Grivence_Management_System.service.ComplaintService;
import com.Grivence_Management_System.service.SolveService;
import com.Grivence_Management_System.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class ComplaintController {

	@Autowired
	private ComplaintService complaintService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SolveService solveService;
	
	@RequestMapping("/savecomplaint/{registrationNumber}")
	public String saveComplaint(@Valid @ModelAttribute("complaint") Complaint complaint,BindingResult result, Model model, @PathVariable("registrationNumber") String registrationNumber) {
		
		Student student = studentService.findStudentByRegistrationNumber(registrationNumber);
		
		if(result.hasErrors()) {
			model.addAttribute("student", student);
     		model.addAttribute("complaint", complaint);
			return "student/complain_page";
		}
		complaintService.complaintsave(registrationNumber, complaint);
		model.addAttribute("sendcomplaint", "Complaint Submit");
		return "redirect:/savecomplaint/" +registrationNumber;
	}
	
	@RequestMapping("/listOfComplaints")
	public String listOfComplaints(Model model) {
		List<Complaint> allComplaints = complaintService.findAllComplaints();
		model.addAttribute("complaints", allComplaints);
		return "admin/all_complaints";
	}
	
	@RequestMapping("/solve/{registrationNumber}/{complaintId}")
	public String solveComplaint(@PathVariable("registrationNumber") String registrationNumber,
			                     @PathVariable("complaintId") String id,
			                     Model model) {
		
		Student student = studentService.findStudentByRegistrationNumber(registrationNumber);
		Complaint complaint = complaintService.findComplaintByComplaintId(id);
		
		Solve solve = new Solve();
		solve.setComplaint(complaint.getComplaint());
		solve.setStudent(student);
		
		solveService.saveSolvedComplaints(solve);
		
		complaintService.deleteComplaintById(id);
		
		List<Complaint> complaints = complaintService.findAllComplaints();
		model.addAttribute("complaints", complaints);
		return "redirect:/studentInfo/"+registrationNumber;
	}
	
}
