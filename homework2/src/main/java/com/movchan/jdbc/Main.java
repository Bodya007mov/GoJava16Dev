package com.movchan.jdbc;

import com.movchan.jdbc.dao.*;
import com.movchan.jdbc.service.*;

import java.text.SimpleDateFormat;

public class Main {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private static final DeveloperService developerService = new DeveloperService(new DeveloperDAO(), new DeveloperSkillService(new DeveloperSkillDAO()), new DeveloperProjectService(new DeveloperProjectDAO()));
    private static final DeveloperSkillService developerSkillService = new DeveloperSkillService(new DeveloperSkillDAO());
    private static final SkillService skillService = new SkillService(new SkillDAO());
    private static final LanguageService languageService = new LanguageService(new LanguageDAO());
    private static final DeveloperProjectService developerProjectService = new DeveloperProjectService(new DeveloperProjectDAO());
    private static final ProjectService projectService = new ProjectService(new ProjectDAO());
    private static final CompanyService companyService = new CompanyService(new CompanyDAO());
    private static final CustomerService customerService = new CustomerService(new CustomerDAO());

    public static void main(String[] args) {
        System.out.println(projectService.getTotalSalaryByProjectId(1L));
        System.out.println(projectService.getDevelopersByProjectId(1L));
        System.out.println(developerService.getJavaDevelopers());
        System.out.println(developerService.getMiddleDevelopers());
        System.out.println(projectService.getProjectDTOs());
    }
}
