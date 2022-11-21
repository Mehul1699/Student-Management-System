package com.masai.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Address;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.beans.StudentSession;
import com.masai.dto.SDTO;
import com.masai.dto.StudentDTO;
import com.masai.services.LoginService;
import com.masai.services.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<StudentSession> loginStudent(@RequestBody StudentDTO dto){
		return new ResponseEntity<StudentSession>(loginService.loginStudent(dto), HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody SDTO student, @RequestParam String uuid, @RequestParam Integer studentCode){
		return new ResponseEntity<Student>(studentService.updateStudent(uuid, studentCode, student), HttpStatus.OK);
	}
	
	@PatchMapping("/update")
	public ResponseEntity<Set<Course>> leaveCourse(@RequestParam String uuid, @RequestParam Integer studentCode, @RequestParam Integer courseId){
		return new ResponseEntity<Set<Course>>(studentService.leaveCourse(uuid, studentCode, courseId), HttpStatus.OK);
	}
	
	@PatchMapping("/saveAddress")
	public ResponseEntity<Set<Address>> updateAddressList(@RequestBody Address address, @RequestParam String uuid, @RequestParam Integer studentCode){
		return new ResponseEntity<Set<Address>>(studentService.updateAddressList(uuid, studentCode, address), HttpStatus.OK);
	}
	
	@GetMapping("/courseList")
	public ResponseEntity<Set<Course>> getCourses(@RequestParam String uuid, @RequestParam Integer studentCode){
		return new ResponseEntity<Set<Course>>(studentService.getCourseList(uuid, studentCode), HttpStatus.OK);
	}
	
	@DeleteMapping("/logout")
	public String logoutStudent(@RequestParam String uuid) {
		return studentService.logoutStudent(uuid);
	}
	
}
