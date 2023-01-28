package com.Grivence_Management_System.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Grivence_Management_System.dto.Login;
import com.Grivence_Management_System.entity.Admin;
import com.Grivence_Management_System.entity.Complaint;
import com.Grivence_Management_System.entity.Student;
import com.Grivence_Management_System.service.AdminService;
import com.Grivence_Management_System.service.ComplaintService;
import com.Grivence_Management_System.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class AdminController {
	
	 AdminService adminService;
	 StudentService studentService;
	 ComplaintService complaintService;
	 
	public AdminController(AdminService adminService,
			               StudentService studentService,
			               ComplaintService complaintService) {
		super();
		this.adminService = adminService;
		this.studentService = studentService;
		this.complaintService = complaintService;
	}

	@RequestMapping("/adminlogin")
	public String adminLogin(Model model) {
		model.addAttribute("adminlogin", new Login());
		return "admin/login_page";
	}
	
	@RequestMapping("/registeradmin")
	public String registerAdmin(Model model) {
		model.addAttribute("admin", new Admin());
		return "admin/register";
	}
	
	@RequestMapping("/saveadmin")
	public String saveAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("admin", admin);
			return "admin/register";
		}
		
		if(!admin.getPassword().equals(admin.getConfirmPassword())) {
			model.addAttribute("error", "Password and confirm password should be same");
			return "admin/register";
		}
		
		Admin existUser = adminService.findByEmail(admin.getEmail());
		if(existUser == null) {
			adminService.registerAdmin(admin);
			model.addAttribute("success", admin.getFirstName());
			return "admin/registration_success";
		}else {
			model.addAttribute("emailerror", "This EMAIL ID is already exist !!");
			return "admin/register";
		}
			
	}
	
	@RequestMapping("/verifyadmin")
	public String verifyAdmin(@ModelAttribute("adminlogin") Login login, Model model) {
		
		Admin existEmail = adminService.findByEmail(login.getEmail());
		if(existEmail != null) {
			if(existEmail.getEmail().equals(login.getEmail()) && existEmail.getPassword().equals(login.getPassword())) {
				model.addAttribute("adminName", "Welcome "+existEmail.getFirstName()+" sir");
				return "admin/admin_controll";
			}
			model.addAttribute("error", "Email id or password is wrong !!");
			return "admin/login_page";

		}
		model.addAttribute("register", "Email Id not found please register..");
		return "admin/login_page";
	}
	
	

}
