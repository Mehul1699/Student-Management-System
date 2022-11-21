package com.masai.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.beans.Address;
import com.masai.beans.Course;
import com.masai.beans.Student;
import com.masai.beans.StudentSession;
import com.masai.dto.SDTO;
import com.masai.exceptions.CourseException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.StudentException;
import com.masai.repo.CourseRepo;
import com.masai.repo.StudentRepo;
import com.masai.repo.StudentSessionRepo;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private StudentSessionRepo studentSessionRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public Student updateStudent(String uuid, Integer studentCode, SDTO student) {
		Optional<StudentSession> op = studentSessionRepo.findByUuid(uuid);
		if(op.isEmpty())
			throw new LoginException("Student not logged in");
		Optional<Student> opt = studentRepo.findById(op.get().getStudentCode());
		if(opt.isEmpty()) {
			throw new StudentException("No student exists with student code: "+studentCode);
		}else {
			Student s = opt.get();
			s.setEmail(student.getEmail());
			s.setMobile(student.getMobile());
			s.setFather_name(student.getFather_name());
			s.setMother_name(student.getMother_name());
			return studentRepo.save(s);
		}
	}

	@Override
	public Set<Course> getCourseList(String uuid, Integer studentCode) {
		Optional<StudentSession> op = studentSessionRepo.findByUuid(uuid);
		if(op.isEmpty())
			throw new LoginException("Student not logged in");
		Optional<Student> opt = studentRepo.findById(studentCode);
		if(opt.isEmpty()) {
			throw new StudentException("No student exists with student code: "+studentCode);
		}else {
			Student s = opt.get();
			Set<Course> set = s.getCourseList();
			if(set.size()!=0) {
				return set;
			}else {
				throw new CourseException("No course assigned to this Student");
			}
		}
	}

	@Override
	public Set<Course> leaveCourse(String uuid, Integer studentCode, Integer courseId) {
		Optional<StudentSession> op = studentSessionRepo.findByUuid(uuid);
		if(op.isEmpty())
			throw new LoginException("Student not logged in");
		Optional<Student> opts = studentRepo.findById(studentCode);
		Optional<Course> optc = courseRepo.findById(courseId);
		if(opts.isEmpty()) {
			throw new StudentException("No student exists with student code: "+studentCode);
		}else {
			if(optc.isEmpty()) {
				throw new CourseException("No course exists with course id: "+courseId);
			}else {
				Student s = opts.get();
				if(s.getCourseList().contains(optc.get())) {
					s.getCourseList().remove(optc.get());
					Student newStudent = studentRepo.save(s);
					return newStudent.getCourseList();
				}else {
					throw new StudentException("Course with course id: "+courseId+" is not assigned to student with student code: "+studentCode);
				}
			}
		}
	}

	@Override
	public String logoutStudent(String uuid) {
		Optional<StudentSession> op = studentSessionRepo.findByUuid(uuid);
		if(op.isEmpty())
			throw new LoginException("Student not logged in");
		studentSessionRepo.delete(op.get());
		return "Student logged out successfully";
	}

	@Override
	public Set<Address> updateAddressList(String uuid, Integer studentCode, Address address) {
		Optional<StudentSession> op = studentSessionRepo.findByUuid(uuid);
		if(op.isEmpty())
			throw new LoginException("Student not logged in");
		Optional<Student> opts = studentRepo.findById(studentCode);
		if(opts.isEmpty()) {
			throw new StudentException("Student doesn't exist with Student Code: "+studentCode);
		}else {
			opts.get().getList().add(address);
			Student st = studentRepo.save(opts.get());
			return st.getList();
		}
	}

}
