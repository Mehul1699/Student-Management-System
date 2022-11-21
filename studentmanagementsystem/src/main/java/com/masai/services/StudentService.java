package com.masai.services;

import java.util.Set;

import com.masai.beans.Address;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.dto.SDTO;

public interface StudentService {

	public Student updateStudent(String uuid, Integer studentCode, SDTO student);
	
	public Set<Address> updateAddressList(String uuid, Integer studentCode, Address address);
	
	public Set<Course> getCourseList(String uuid, Integer studentCode);
	
	public Set<Course> leaveCourse(String uuid, Integer studentCode, Integer courseId);
	
	public String logoutStudent(String uuid);
	
}
