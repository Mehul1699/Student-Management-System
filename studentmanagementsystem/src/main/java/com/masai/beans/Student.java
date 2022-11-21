package com.masai.beans;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer studentCode;
	
	@NotNull(message = "Name cannot be NULL")
	@Pattern(regexp = "[a-zA-Z]")
	private String name;
	
	@NotNull(message = "DOB can't be null")
	private String date_of_birth;
	
	@NotNull(message="This field can't be NULL")
	private String gender;
	
	@NotNull(message="This field can't be NULL")
	@Pattern(regexp = "{0-9}")
	private String mobile;
	
	@NotNull(message="This field can't be NULL")
	private String father_name;
	
	@NotNull(message="This field can't be NULL")
	private String mother_name;
	
	@NotNull(message="This field can't be NULL")
	@Email
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "student")
	private Set<Address> list=new HashSet<>();
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="stList")
	private Set<Course> courseList = new HashSet<>();
	
}
