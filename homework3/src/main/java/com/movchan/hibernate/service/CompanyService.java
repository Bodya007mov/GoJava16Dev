package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.CompanyDAO;
import com.movchan.hibernate.domain.Company;
import com.movchan.hibernate.service.dto.CompanyDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class CompanyService {

    private final CompanyDAO companyDAO;

    public List<Company> getAllCompanies() {
        return companyDAO.getAll();
    }

    public Company getCompanyById(Long id) {
        return companyDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find company with id = " + id));
    }

    public void deleteCompanyById(Long id) {
        companyDAO.deleteById(id);
    }

    public void createCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setPhone(companyDTO.getPhone());
        companyDAO.create(company);
    }

    public void updateCompany(CompanyDTO companyDTO) {
        Company company = new Company();
        Long id = companyDTO.getId();
        company.setId(id);
        company.setName(companyDTO.getName());
        company.setPhone(companyDTO.getPhone());
        companyDAO.update(company, id);
    }
}
