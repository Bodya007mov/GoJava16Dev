package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    public Developer(String name, Integer age, Sex sex, Double salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    private Long id;

    private String name;

    private Integer age;

    private Sex sex;

    private Double salary;
}
