package com.innoventes.test.app.controller;

import java.net.URI;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.innoventes.test.app.dto.CompanyDTO;
import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.intercepter.WebsiteURLValidationInterceptor;
import com.innoventes.test.app.mapper.CompanyMapper;
import com.innoventes.test.app.service.CompanyService;
@RestController
@Validated
@RequestMapping("/api/v1")
public class CompanyController {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private MessageSource messageSource;

	@GetMapping("/companies")
	public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
		List<Company> companyList = companyService.getAllCompanies();
		
		List<CompanyDTO> companyDTOList = new ArrayList<CompanyDTO>();
		
		for (Company entity : companyList) {
			companyDTOList.add(companyMapper.getCompanyDTO(entity));
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(companyDTOList);
	}
	
	
//	@PostMapping("/companies")
//    public ResponseEntity<CompanyDTO> addCompany(@Validated @RequestBody CompanyDTO companyDTO,
//                                                 HttpServletRequest request) throws ValidationException {
//		 System.out.println("Received CompanyDTO: " + companyDTO);
//        request.setAttribute("companyDTO", companyDTO);
//	
//	    Company company = companyMapper.getCompany(companyDTO);
//	    Company newCompany = companyService.addCompany(company);
//	    CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);
//
//	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
//	            .toUri();
//	    return ResponseEntity.created(location).body(newCompanyDTO);
//	}
	
	@Autowired
	private WebsiteURLValidationInterceptor websiteURLValidationInterceptor;
	
//	@PostMapping("/companies")
//	public ResponseEntity<CompanyDTO> addCompany(@Validated @RequestBody CompanyDTO companyDTO,
//	                                             HttpServletRequest request) throws ValidationException {
//	    System.out.println("Received CompanyDTO: " + companyDTO);
//
//	    // Set companyDTO attribute in the request
//	    request.setAttribute("companyDTO", companyDTO);
//
//	    try {
//	        // Apply the interceptor to modify the webSiteURL field if needed
//	        websiteURLValidationInterceptor.preHandle(request, null, null);
//	    } catch (Exception e) {
//	        // Handle the exception (you can log it or take appropriate action)
//	        e.printStackTrace();
//	    }
//
//	    Company company = companyMapper.getCompany(companyDTO);
//	    Company newCompany = companyService.addCompany(company);
//	    CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);
//
//	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
//	            .toUri();
//	    return ResponseEntity.created(location).body(newCompanyDTO);
//	}
	
	@PostMapping("/companies")
	public ResponseEntity<CompanyDTO> addCompany(@Validated @RequestBody CompanyDTO companyDTO,
	                                             HttpServletRequest request) throws ValidationException {
	    System.out.println("Received CompanyDTO: " + companyDTO);

	    // Set CompanyDTO as an attribute in the request
	    request.setAttribute("companyDTO", companyDTO);

	    // Apply the interceptor to modify the webSiteURL field
	    try {
			websiteURLValidationInterceptor.preHandle(request, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    Company company = companyMapper.getCompany(companyDTO);
	    Company newCompany = companyService.addCompany(company);
	    CompanyDTO newCompanyDTO = companyMapper.getCompanyDTO(newCompany);

	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCompany.getId())
	            .toUri();
	    return ResponseEntity.created(location).body(newCompanyDTO);
	}


	
	
	@PutMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> updateCompany(@PathVariable(value = "id") Long id,
			@Valid @RequestBody CompanyDTO companyDTO) throws ValidationException {
		Company company = companyMapper.getCompany(companyDTO);
		Company updatedCompany = companyService.updateCompany(id, company);
		CompanyDTO updatedCompanyDTO = companyMapper.getCompanyDTO(updatedCompany);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.status(HttpStatus.OK).location(location).body(updatedCompanyDTO);
	}

	@DeleteMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> deleteCompany(@PathVariable(value = "id") Long id) {
		companyService.deleteCompany(id);
		return ResponseEntity.noContent().build();
	}
	
	 
	 
	 @GetMapping(value = "/companies/{id}")
	public ResponseEntity<CompanyDTO> getUserById(@PathVariable(value = "id") Long id) {
		 Company companyById = companyService.getCompanyById(id);
		 CompanyDTO byIdCompanyDTO = companyMapper.getCompanyDTO(companyById);
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
			return ResponseEntity.status(HttpStatus.OK).location(location).body(byIdCompanyDTO);
	 
	 }
	 
	   
	    @PatchMapping("/companies/{id}")
	    public ResponseEntity<CompanyDTO> partialUpdateCompany(
	            @PathVariable(value = "id") Long id,
	            @RequestBody CompanyDTO companyDTO) throws ValidationException {
	    	Company company = companyMapper.getCompany(companyDTO);
	        Company updatedCompany = companyService.partialUpdateCompany(id, company);
	        CompanyDTO updatedCompanyDTO = companyMapper.getCompanyDTO(updatedCompany);
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
	        return ResponseEntity.status(HttpStatus.OK).location(location).body(updatedCompanyDTO);
	    }
	    
	    @GetMapping("/companies/by-code/{companyCode}")
	    public ResponseEntity<CompanyDTO> getCompanyByCode(@PathVariable String companyCode) {
	        Company company = companyService.getCompanyByCompanyCode(companyCode);
	        CompanyDTO companyDTO = companyMapper.getCompanyDTO(company);
	        return ResponseEntity.status(HttpStatus.OK).body(companyDTO);
	    }


	
}
