package com.masai.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Admin;
import com.masai.beans.AdminSession;
import com.masai.beans.Student;
import com.masai.beans.StudentSession;
import com.masai.dto.AdminDTO;
import com.masai.dto.StudentDTO;
import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.StudentException;
import com.masai.repo.AdminRepo;
import com.masai.repo.AdminSessionRepo;
import com.masai.repo.StudentRepo;
import com.masai.repo.StudentSessionRepo;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private StudentSessionRepo studentSessionRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;
	
	@Override
	public AdminSession loginAdmin(AdminDTO login) {
		Optional<Admin> res = adminRepo.findByUsername(login.getUsername());
		if(res.isEmpty()) {
			throw new AdminException("Admin doesn't exist");
		}
		Admin existingAdmin = res.get();
		Optional<AdminSession> opt = adminSessionRepo.findByAdminId(existingAdmin.getAdminId());
		
		if(opt.isPresent()) {
			throw new LoginException("Admin already logged in");
		}
		if(existingAdmin.getPassword().equals(login.getPassword())) {
			
			AdminSession newSession = new AdminSession();
			
			newSession.setAdminId(existingAdmin.getAdminId());
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String rtc = uuid.toString().split("-")[0];
			
			newSession.setUuid(rtc);
			
			return adminSessionRepo.save(newSession);
			
		}else {
			throw new LoginException("Login not possible. Password incorrect");
		}
	}

	@Override
	public StudentSession loginStudent(StudentDTO login) {
		Optional<Student> res = studentRepo.findByStudentCode(login.getStudentCode());
		if(res.isEmpty())
			throw new StudentException("Student doesn't exist");
		Student existingStudent = res.get();
		Optional<StudentSession> opt = studentSessionRepo.findByStudentCode(existingStudent.getStudentCode());
		
		if(opt.isPresent()) {
			throw new LoginException("Student already logged in");
		}
		if(existingStudent.getDate_of_birth().equals(login.getDob())) {
			
			StudentSession newSession = new StudentSession();
			
			newSession.setStudentCode(existingStudent.getStudentCode());
			newSession.setSessionStartTime(LocalDateTime.now());
			newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
			
			UUID uuid = UUID.randomUUID();
			String rtc = uuid.toString().split("-")[0];
			
			newSession.setUuid(rtc);
			
			return studentSessionRepo.save(newSession);
			
		}else {
			throw new LoginException("Login not possible. DOB didn't match");
		}
	}

}
