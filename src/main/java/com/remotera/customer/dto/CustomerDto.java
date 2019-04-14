package com.remotera.customer.dto;

import javax.validation.constraints.NotNull;

import com.remotera.customer.domain.Country;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
public class CustomerDto {

	private String id;
	@NotNull(message= "Title name is mandatory")
	private String title;
	@NotNull(message= "First name is mandatory")
	private String firstName;
	@NotNull(message= "Last name is mandatory")
	private String lastName;
	@NotNull(message= "Address is mandatory")
	private String address;
	@NotNull(message= "Landmark is mandatory")
	private String landmark;
	private Country country;
	private String notes;
	private String mobileNumber;
	private String email;

}
