package com.masai.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SDTO {
	
	@NotNull(message="This field can't be null")
	@Pattern(regexp = "[a-zA-Z]")
	@Size(min=3)
	private String name;
	
	@NotNull(message="This field can be null")
	private String dob;
	
	@NotNull(message="This field can't be NULL")
	private String gender;
	
	@NotNull(message="This field can be null")
	private String mobile;
	
	@NotNull(message="This field can't be NULL")
	private String father_name;
	
	@NotNull(message="This field can't be NULL")
	private String mother_name;
	
	@NotNull(message="This field can't be NULL")
	@Email
	private String email;
	
	
}
