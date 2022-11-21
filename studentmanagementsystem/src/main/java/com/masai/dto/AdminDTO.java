package com.masai.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String mobile;
	
}
