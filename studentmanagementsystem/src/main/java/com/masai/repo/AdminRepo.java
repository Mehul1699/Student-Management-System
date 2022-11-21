package com.masai.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Optional<Admin> findByUsername(String username);
	
	public Admin findByMobile(String mobile);
	
}
