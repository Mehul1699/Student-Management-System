package com.masai.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer addId;

	@NotNull(message="This field can't be NULL")
	private String area;
	
	@NotNull(message="This field can't be NULL")
	private String state;
	
	@NotNull(message="This field can't be NULL")
	private String district;
	
	@NotNull(message="This field can't be NULL")
	
	private Integer pincode;
	
	@NotNull(message="This field can't be NULL")
	private String addressType;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Student student;
	
}
