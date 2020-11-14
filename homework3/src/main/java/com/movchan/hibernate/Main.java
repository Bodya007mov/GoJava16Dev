package com.movchan.hibernate;

import com.movchan.hibernate.domain.Sex;
import com.movchan.hibernate.service.ServiceFactory;
import com.movchan.hibernate.service.dto.DeveloperDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final ServiceFactory serviceFactory = new ServiceFactory();

    public static void main(String[] args) {
        List<Long> skills = new ArrayList<>();
        skills.add(1L);
        skills.add(2L);
        List<Long> projects = new ArrayList<>();
        projects.add(3L);
        projects.add(4L);
        DeveloperDTO developerDTO = new DeveloperDTO("Test", 777, Sex.FEMALE, BigDecimal.valueOf(9999.563), skills, projects);
        serviceFactory.getDeveloperService().createDeveloper(developerDTO);

        System.out.println(serviceFactory.getDeveloperService().getAllDevelopers());
    }
}
