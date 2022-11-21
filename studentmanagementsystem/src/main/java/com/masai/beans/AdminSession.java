package com.masai.beans;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AdminSession {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer sessionId;
	private Integer adminId;
	private String uuid;
	private LocalDateTime sessionStartTime;
	private LocalDateTime sessionEndTime;
	
}
