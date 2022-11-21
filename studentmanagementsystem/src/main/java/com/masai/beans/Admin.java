package com.masai.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer adminId;
	
	@NotNull(message="Username cannot be NULL")
	@Size(min=3, message="length must be >=3")
	private String username;
	
	@NotNull(message="Please enter the password")
	@Pattern(regexp = "[A-Za-z0-9!@#$%^&*_]{8,15}", message = "Password must be 8-15 characters in length and can include A-Z, a-z, 0-9, or special characters !@#$%^&*_")
	private String password;
	
	@NotNull(message="Please enter the mobile number")
	@Column(unique=true)
	private String mobile;
	
	@NotNull(message="Please enter the email id")
	@Column(unique=true)
	@Email
	private String email;
	
}
