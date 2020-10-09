package com.movchan.jdbc.service;

import com.movchan.jdbc.dao.CompanyDAO;
import com.movchan.jdbc.domain.Company;
import com.movchan.jdbc.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

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

    public void createCompany(Company company) {
        companyDAO.create(company);
    }

    public void updateCompany(Company company) {
        companyDAO.update(company);
    }
}
