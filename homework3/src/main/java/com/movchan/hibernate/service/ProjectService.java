package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.ProjectDAO;
import com.movchan.hibernate.domain.Company;
import com.movchan.hibernate.domain.Customer;
import com.movchan.hibernate.domain.Project;
import com.movchan.hibernate.service.dto.ProjectDTO;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDAO projectDAO;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public List<Project> getAllProjects() {
        return projectDAO.getAll();
    }

    public Project getProjectById(Long id) {
        return projectDAO.getById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find project with id = " + id));
    }

    public void deleteProjectById(Long id) {
        projectDAO.deleteById(id);
    }

    public void createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());
        project.setCreationDate(projectDTO.getCreationDate());
        Long companyId = projectDTO.getCompanyId();
        Company company = companyService.getCompanyById(companyId);
        project.setCompany(company);
        Long customerId = projectDTO.getCustomerId();
        Customer customer = customerService.getCustomerById(customerId);
        project.setCustomer(customer);
        project.setCost(projectDTO.getCost());
        projectDAO.create(project);
    }

    public void updateProject(ProjectDTO projectDTO) {
        Project project = new Project();
        Long id = projectDTO.getId();
        project.setId(id);
        project.setName(projectDTO.getName());
        project.setCreationDate(projectDTO.getCreationDate());
        Long companyId = projectDTO.getCompanyId();
        Company company = companyService.getCompanyById(companyId);
        project.setCompany(company);
        Long customerId = projectDTO.getCustomerId();
        Customer customer = customerService.getCustomerById(customerId);
        project.setCustomer(customer);
        project.setCost(projectDTO.getCost());
        projectDAO.update(project, id);
    }
}
