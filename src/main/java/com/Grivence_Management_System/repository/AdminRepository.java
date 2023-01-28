package com.Grivence_Management_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Grivence_Management_System.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	Admin findByEmail(String email);

}
