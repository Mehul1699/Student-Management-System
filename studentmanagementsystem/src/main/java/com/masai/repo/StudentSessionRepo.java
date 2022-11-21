package com.masai.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Student;
import com.masai.beans.StudentSession;

@Repository
public interface StudentSessionRepo extends JpaRepository<StudentSession, Integer>{

	public Optional<StudentSession> findByStudentCode(Integer studentCode);
	
	public Optional<StudentSession> findByUuid(String uuid);
	
}
