package com.masai.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CDTO {

	@NotNull(message="This field can't be NULL")
	private String courseName;
	
	@NotNull(message="This field can't be NULL")
	private String description;
	
	@NotNull(message="This field can't be NULL")
	private String type;
	
	@NotNull(message="This field can't be NULL")
	private Integer duration;
	
}
