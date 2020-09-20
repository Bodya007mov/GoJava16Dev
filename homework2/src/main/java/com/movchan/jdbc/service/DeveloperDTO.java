package com.movchan.jdbc.service;

import com.movchan.jdbc.domain.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeveloperDTO {

    private String name;

    private Integer age;

    private Sex sex;

    private Double salary;

    private List<Long> skills;
}
