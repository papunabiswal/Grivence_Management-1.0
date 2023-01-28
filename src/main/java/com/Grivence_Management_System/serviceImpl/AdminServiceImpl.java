package com.Grivence_Management_System.serviceImpl;

import org.springframework.stereotype.Service;

import com.Grivence_Management_System.entity.Admin;
import com.Grivence_Management_System.repository.AdminRepository;
import com.Grivence_Management_System.service.AdminService;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService {
	
	private AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}

	@Override
	public void registerAdmin(@Valid Admin admin) {
		adminRepository.save(admin);
		
	}

	
}
