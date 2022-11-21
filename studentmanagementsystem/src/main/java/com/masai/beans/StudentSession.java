package com.masai.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class StudentSession {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sessionId;
	private Integer studentCode;
	private LocalDate date_of_birth;
	private String uuid;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;
	
}
