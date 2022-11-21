package com.masai.services;

import java.util.Set;

import com.masai.beans.Address;
import com.masai.beans.Admin;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.dto.CDTO;
import com.masai.dto.SDTO;

public interface AdminService {

	public Admin registerAdmin(Admin admin);
	
	public Student saveStudent(String uuid, SDTO dto);
	
	public Course saveCourse(String uuid, CDTO course);
	
	public Set<Address> saveAddress(String uuid, Integer studentCode, Address address);
	
	public Student findStudentByName(String uuid, String name);
	
	public Student addCourseToStudent(String uuid, Integer studentCode, Integer courseId);
	
	public Course addStudentToCourse(String uuid, Integer studentCode, Integer courseId);
	
	public String logoutAdmin(String key);
	
}
