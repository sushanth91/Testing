package com.innoventes.test.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.innoventes.test.app.entity.Company;
import com.innoventes.test.app.error.ApplicationErrorCodes;
import com.innoventes.test.app.exception.ResourceNotFoundException;
import com.innoventes.test.app.exception.ValidationException;
import com.innoventes.test.app.repository.CompanyRepository;
import com.innoventes.test.app.service.CompanyService;
import com.innoventes.test.app.util.ServiceHelper;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private ServiceHelper serviceHelper;

	@Override
	public List<Company> getAllCompanies() {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyRepository.findAll().forEach(companyList::add);
		return companyList;
	}

	@Override
	public Company addCompany(Company company) throws ValidationException {
		return companyRepository.save(company);
	}

	@Override
	public Company updateCompany(Long id, Company company) throws ValidationException {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		company.setId(existingCompanyRecord.getId());
		return companyRepository.save(company);
	}

	@Override
	public void deleteCompany(Long id) {
		Company existingCompanyRecord = companyRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
						ApplicationErrorCodes.COMPANY_NOT_FOUND));
		companyRepository.deleteById(existingCompanyRecord.getId());
	}
	

	
	  @Override
	    public Company getCompanyById(Long id) {
		  Company existingCompanyRecord = companyRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException(
							String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
							ApplicationErrorCodes.COMPANY_NOT_FOUND));
	        return existingCompanyRecord ;
	    }

	
	  @Override
	    public Company partialUpdateCompany(Long id, Company company) throws ValidationException {
	        Company existingCompanyRecord = companyRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(
	                        String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), id),
	                        ApplicationErrorCodes.COMPANY_NOT_FOUND));

	        // Apply partial updates based on non-null fields in companyDTO
	        if (company.getCompanyName() != null) {
	            existingCompanyRecord.setCompanyName(company.getCompanyName());
	        }
	        if (company.getEmail() != null) {
	            existingCompanyRecord.setEmail(company.getEmail());
	        }
	        if (company.getStrength() != null) {
	            existingCompanyRecord.setStrength(company.getStrength());
	        }
	        if (company.getWebSiteURL() != null) {
	            existingCompanyRecord.setWebSiteURL(company.getWebSiteURL());
	        }
	        if (company.getCompanyCode() != null) {
	            existingCompanyRecord.setCompanyCode(company.getCompanyCode());
	        }

	        return companyRepository.save(existingCompanyRecord);
	    }
	  
	  @Override
	  public Company getCompanyByCompanyCode(String companyCode) {
	      return companyRepository.findByCompanyCode(companyCode)
	              .orElseThrow(() -> new ResourceNotFoundException(
	                      String.format(serviceHelper.getLocalizedMessage(ApplicationErrorCodes.COMPANY_NOT_FOUND), companyCode),
	                      ApplicationErrorCodes.COMPANY_NOT_FOUND));
	  }


	
	}

