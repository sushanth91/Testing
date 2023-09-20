package com.innoventes.test.app.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import com.innoventes.test.app.customvalidation.EvenNumberOrZero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CompanyDTO {

	private Long id;
	
    @NotBlank(message = "Company name is mandatory")
    @Size(max = 5, message = "Company name can have a maximum of 5 characters")
    private String companyName;

	
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
    @NotNull(message = "This field is mandatory")
    private String email;

	@PositiveOrZero(message = "Strength Value must be positive or zero")
	@EvenNumberOrZero
	private Integer strength;
	
	private String webSiteURL;
	
	@Size(min = 5, message = "Company code length should be at least 5 characters")
	@Pattern(regexp = "^[a-zA-Z]{2}[0-9]{2}[ENen]$", message = "Invalid company code format")
	private String companyCode;
	
	
		
}
