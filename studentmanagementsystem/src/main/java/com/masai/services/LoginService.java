package com.masai.services;

import com.masai.beans.AdminSession;
import com.masai.beans.StudentSession;
import com.masai.dto.AdminDTO;
import com.masai.dto.StudentDTO;

public interface LoginService {

	public AdminSession loginAdmin(AdminDTO login);
	
	public StudentSession loginStudent(StudentDTO login);
	
}
