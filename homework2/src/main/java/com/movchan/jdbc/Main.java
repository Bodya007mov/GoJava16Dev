package com.movchan.jdbc;

import com.movchan.jdbc.dao.DeveloperDAO;
import com.movchan.jdbc.dao.DeveloperSkillDAO;
import com.movchan.jdbc.domain.Developer;
import com.movchan.jdbc.domain.Sex;
import com.movchan.jdbc.service.DeveloperDTO;
import com.movchan.jdbc.service.DeveloperService;
import com.movchan.jdbc.service.DeveloperSkillService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final DeveloperService developerService = new DeveloperService(new DeveloperDAO(), new DeveloperSkillService(new DeveloperSkillDAO()));

    public static void main(String[] args) {
//        Long testId;
//        System.out.println(testId = developerService.createDeveloper(new Developer("test", 50, Sex.FEMALE, 100000.0)));
//        System.out.println(developerService.getAllDevelopers());
//        developerService.updateDeveloper(new Developer(testId, "testNew1", 51, Sex.MALE, 100.0));
//        System.out.println(developerService.getAllDevelopers());
//        System.out.println(developerService.getDeveloperById(1L));
//        System.out.println(developerService.getAllDevelopers());
//        List<Long> skills = new ArrayList<>();
//        skills.add(5L);
//        skills.add(6L);
//        skills.add(7L);
//        developerService.createDeveloper(new DeveloperDTO("test!!!", 666, Sex.MALE, 6666D, skills));
        developerService.deleteDeveloperById(7L);
    }
}
