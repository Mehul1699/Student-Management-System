package com.masai.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Address;
import com.masai.beans.Admin;
import com.masai.beans.AdminSession;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.dto.CDTO;
import com.masai.dto.SDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.StudentException;
import com.masai.repo.AdminRepo;
import com.masai.repo.AdminSessionRepo;
import com.masai.repo.CourseRepo;
import com.masai.repo.StudentRepo;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepo adminRepo;
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;
	
	@Override
	public Admin registerAdmin(Admin admin) {
		return adminRepo.save(admin);
	}

	@Override
	public Student saveStudent(String uuid, SDTO student) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Student st = new Student();
		st.setName(student.getName());
		st.setEmail(student.getEmail());
		st.setDate_of_birth(student.getDob());
		st.setGender(student.getGender());
		st.setMobile(student.getMobile());
		st.setFather_name(student.getFather_name());
		st.setMother_name(student.getMother_name());
		return studentRepo.save(st);
	}

	@Override
	public Course saveCourse(String uuid, CDTO course) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Course cr = new Course();
		cr.setCourseName(course.getCourseName());
		cr.setDescription(course.getDescription());
		cr.setDuration(course.getDuration());
		cr.setType(course.getType());
		return courseRepo.save(cr);
	}

	@Override
	public Student findStudentByName(String uuid, String name) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Student st = studentRepo.findByName(name);
		if(st!=null) {
			return st;
		}else {
			throw new StudentException("Student doesn't exist with name: "+name);
		}
	}

	@Override
	public Student addCourseToStudent(String uuid, Integer studentCode, Integer courseId) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Optional<Student> opts = studentRepo.findById(studentCode);
		Optional<Course> optc = courseRepo.findById(courseId);
		if(opts.isEmpty()) {
			throw new StudentException("No student with student code: "+studentCode);
		}else {
			if(optc.isEmpty()) {
				throw new CourseException("No course with ID: "+courseId);
			}else {
				Student s = opts.get();
				Course c = optc.get();
				s.getCourseList().add(c);
				c.getStList().add(s);
				return studentRepo.save(s);
			}
		}
	}

	@Override
	public Course addStudentToCourse(String uuid, Integer studentCode, Integer courseId) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Optional<Student> opts = studentRepo.findById(studentCode);
		Optional<Course> optc = courseRepo.findById(courseId);
		if(opts.isEmpty()) {
			throw new StudentException("No student with student code: "+studentCode);
		}else {
			if(optc.isEmpty()) {
				throw new CourseException("No course with ID: "+courseId);
			}else {
				Student s = opts.get();
				Course c = optc.get();
				c.getStList().add(s);
				s.getCourseList().add(c);
				return courseRepo.save(c);
			}
		}
	}

	@Override
	public String logoutAdmin(String key) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(key);
		if(opt.isEmpty())
			throw new LoginException("Admin is not logged in");
		adminSessionRepo.delete(opt.get());
		return "Admin Logged out successfully";
	}

	@Override
	public Set<Address> saveAddress(String uuid, Integer studentCode, Address address) {
		Optional<AdminSession> opt = adminSessionRepo.findByUuid(uuid);
		if(opt.isEmpty())
			throw new LoginException("Admin not logged in");
		Optional<Student> opts = studentRepo.findById(studentCode);
		if(opts.isEmpty()) {
			throw new StudentException("Student doesn't exist with student code: "+studentCode);
		}else {
			opts.get().getList().add(address);
			Student st = studentRepo.save(opts.get());
			return st.getList();
		}
	}

}
