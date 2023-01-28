package com.Grivence_Management_System.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Grivence_Management_System.dto.Login;
import com.Grivence_Management_System.entity.Complaint;
import com.Grivence_Management_System.entity.Student;
import com.Grivence_Management_System.service.ComplaintService;
import com.Grivence_Management_System.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ComplaintService complaintService;
	
	@RequestMapping("/")
	public String homePage() {
		return "home";
	}
	
	@RequestMapping("/register")
	public String registration(Model model) {
		model.addAttribute("student", new Student());
		return "student/register";
	}
	
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute("student") Student student, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("student", student);
			return "student/register";
		}
		
		if(!student.getPassword().equals(student.getConfirmPassword())) {
			model.addAttribute("error", "Password and confirm password should be same");
			return "student/register";
		}
		
		Student existingStudent = studentService.findStudentByRegistrationNumber(student.getRegistrationNumber());
		if(existingStudent == null) {
			
			Student existUser = studentService.findByEmailId(student.getEmail());
			if(existUser == null) {
				studentService.register(student);
				model.addAttribute("success", student.getFirstName());
				return "student/registration_success";
			}else {
				model.addAttribute("emailerror", "This EMAIL ID is already exist !!");
				return "student/register";
			}
			
		}else {
			model.addAttribute("registrationNumberError", "This Registration Number is already exist !!");
			return "student/register";
		}
			
	}
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
	    Login login = new Login();
		model.addAttribute("login", login);
		return "student/login_page";
	}

	@RequestMapping("/verify")
	public String loginVerify(Login login, Model model) {
		Student student = new Student();
		
		Student existEmail = studentService.findByEmailId(login.getEmail());
		if(existEmail !=null) {
			
			if(existEmail.getEmail().equals(login.getEmail()) && existEmail.getPassword().equals(login.getPassword())) {
				model.addAttribute("complaint", new Complaint());
				model.addAttribute("student", existEmail);
				return "student/complain_page";
			}
			model.addAttribute("error", "Email id or password is wrong !!");
			return "student/login_page";
		}
		model.addAttribute("register", "Email Id not found please register..");
		return "student/login_page";
	}
	
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
		 HttpSession session = request.getSession(false);
		    if (session != null) {
		        session.invalidate();
		    }
        return "redirect:/login?success";
    }
	
	@RequestMapping("/listOfStudents")
	public String listOfStudents(Model model) {
		List<Student> allStudents = studentService.findAllStudents();
		model.addAttribute("students", allStudents);
		return "admin/all_students";
	}
	
    @RequestMapping("/studentInfo/{registrationNumber}")	
    public String studentInfo(@PathVariable("registrationNumber") String registrationNumber, Model model) {
    	Student studentInfo = studentService.findStudentByRegistrationNumber(registrationNumber);
    	List<Complaint> complaints = complaintService.findComplaintsByStudentId(studentInfo.getId());
    	model.addAttribute("studentInfo", studentInfo);
    	model.addAttribute("complaints", complaints);
    	return "admin/student_info";
    }
    
    @RequestMapping("/deleteStudent/{id}")
    public String deleteStudentById(@PathVariable("id") long id, Model model) {
    	studentService.deleteStudentById(id);
    	List<Student> allStudents = studentService.findAllStudents();
		model.addAttribute("students", allStudents);
		return "admin/all_students";
    }
	
	@RequestMapping("/updateStudent/{registrationNumber}")
	public String updateStudent(@PathVariable("registrationNumber") String registrationNumber,
			                    Model model) {
		Student existStudent = studentService.findStudentByRegistrationNumber(registrationNumber);
		model.addAttribute("student", existStudent);
		return "admin/update_student_info";
	}
	
	@RequestMapping("/saveUpdate/{id}")
	public String updateStudent(@Valid @ModelAttribute("student") Student student,
			                    BindingResult result,
			                    Model model,
			                    @PathVariable("id") long id) {
		
		if(result.hasErrors()) {
			model.addAttribute("student", student);
			return "admin/update_student_info";
		}
		
		if(!student.getPassword().equals(student.getConfirmPassword())) {
			model.addAttribute("error", "Password and confirm password should be same");
			return "admin/update_student_info";
		}
		        student.setId(id);
				studentService.updateStudent(student);
				List<Student> allStudents = studentService.findAllStudents();
				model.addAttribute("students", allStudents);
				return "admin/all_students";
	}
	
	@RequestMapping("/search")
	public String searchStudents(@RequestParam(value="query") String query, Model model) {
		List<Student> allStudents = studentService.searchStudents(query);
		model.addAttribute("students", allStudents);
		return "admin/all_students";
	}
    
}
