package com.movchan.jdbc.service.dto;

import com.movchan.jdbc.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class DeveloperDTO {

    private Long id;

    private String name;

    private Integer age;

    private Sex sex;

    private BigDecimal salary;

    private List<Long> skills;

    private List<Long> projects;

    public DeveloperDTO(String name, Integer age, Sex sex, BigDecimal salary, List<Long> skills, List<Long> projects) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
        this.skills = skills;
        this.projects = projects;
    }
}
