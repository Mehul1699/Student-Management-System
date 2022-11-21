package com.masai.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.AdminSession;

@Repository
public interface AdminSessionRepo extends JpaRepository<AdminSession, Integer>{

	public Optional<AdminSession> findByAdminId(Integer adminId);
	
	public Optional<AdminSession> findByUuid(String uuid);
	
}
