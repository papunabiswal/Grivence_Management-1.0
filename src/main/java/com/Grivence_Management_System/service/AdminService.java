package com.Grivence_Management_System.service;

import com.Grivence_Management_System.entity.Admin;

import jakarta.validation.Valid;

public interface AdminService {

	Admin findByEmail(String email);

	void registerAdmin(@Valid Admin admin);


}
