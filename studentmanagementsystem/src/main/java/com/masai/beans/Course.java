package com.masai.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer courseId;
	
	@NotNull(message="This field can't be NULL")
	private String courseName;
	
	@NotNull(message="This field can't be NULL")
	private String description;
	
	@NotNull(message="This field can't be NULL")
	private String type;
	
	@NotNull(message="This field can't be NULL")
	private Integer duration;
	
//	private List<String> topics = new ArrayList<>();
	
	@ManyToMany(cascade=CascadeType.ALL)
	private Set<Student> stList = new HashSet<>();
	
}
