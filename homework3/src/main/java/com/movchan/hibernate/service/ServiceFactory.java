package com.movchan.hibernate.service;

import com.movchan.hibernate.dao.*;

public class ServiceFactory {

    public DeveloperService getDeveloperService() {
        return new DeveloperService(new DeveloperDAO(), new ServiceFactory());
    }

    public SkillService getSkillService() {
        return new SkillService(new SkillDAO(), new LanguageService(new LanguageDAO()));
    }

    public ProjectService getProjectService() {
        return new ProjectService(new ProjectDAO(),
                new CompanyService(new CompanyDAO()),
                new CustomerService(new CustomerDAO()));
    }

    public LanguageService getLanguageService() {
        return new LanguageService(new LanguageDAO());
    }

    public CompanyService getCompanyService() {
        return new CompanyService(new CompanyDAO());
    }

    public CustomerService getCustomerService() {
        return new CustomerService(new CustomerDAO());
    }
}
