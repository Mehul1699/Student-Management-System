package com.masai.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.beans.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{

	public Optional<Student> findByStudentCode(Integer studentCode);
	
	public Student findByEmail(String email);
	
	public Student findByName(String name);
	
}
