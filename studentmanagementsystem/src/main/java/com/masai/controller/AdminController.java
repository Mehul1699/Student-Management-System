package com.masai.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.beans.Address;
import com.masai.beans.Admin;
import com.masai.beans.AdminSession;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.dto.AdminDTO;
import com.masai.dto.CDTO;
import com.masai.dto.SDTO;
import com.masai.services.AdminService;
import com.masai.services.LoginService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){
		Admin ad = adminService.registerAdmin(admin);
		return new ResponseEntity<Admin>(ad, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AdminSession> loginAdmin(@RequestBody AdminDTO dto){
		
		return new ResponseEntity<AdminSession>(loginService.loginAdmin(dto), HttpStatus.CREATED);
		
	}
	
	@PostMapping("/saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody SDTO student, @RequestParam String uuid){
		return new ResponseEntity<Student>(adminService.saveStudent(uuid, student), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveCourse")
	public ResponseEntity<Course> saveCourse(@RequestBody CDTO course, @RequestParam String uuid){
		return new ResponseEntity<Course>(adminService.saveCourse(uuid, course), HttpStatus.CREATED);
	}
	
	@PatchMapping("/saveAddress")
	public ResponseEntity<Set<Address>> saveAddressToStudent(@RequestBody Address address, @RequestParam String uuid, @RequestParam Integer studentCode){
		return new ResponseEntity<Set<Address>>(adminService.saveAddress(uuid, studentCode, address), HttpStatus.OK);
	}
	
	@PatchMapping("/addStudent")
	public ResponseEntity<Course> addStudentToCourse(@RequestParam String uuid, @RequestParam Integer studentCode, @RequestParam Integer courseId){
		return new ResponseEntity<Course>(adminService.addStudentToCourse(uuid, studentCode, courseId), HttpStatus.OK);
	}
	
	@PatchMapping("/addCourse")
	public ResponseEntity<Student> addCourseToStudent(@RequestParam String uuid, @RequestParam Integer studentCode, @RequestParam Integer courseId){
		return new ResponseEntity<Student>(adminService.addCourseToStudent(uuid, studentCode, courseId), HttpStatus.OK);
	}
	
	@GetMapping("/getStudent/{name}")
	public ResponseEntity<Student> getStudentByName(@RequestParam String uuid, @PathVariable("name") String name){
		return new ResponseEntity<Student>(adminService.findStudentByName(uuid, name), HttpStatus.OK);
	}
	
	@DeleteMapping("/logout")
	public String adminLogout(@RequestParam String uuid) {
		return adminService.logoutAdmin(uuid);
	}
	
}
