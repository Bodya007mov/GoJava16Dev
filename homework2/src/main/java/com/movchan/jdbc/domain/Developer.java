package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Developer {

    private Long id;

    private String name;

    private Integer age;

    private Sex sex;

    private BigDecimal salary;

    public Developer(String name, Integer age, Sex sex, BigDecimal salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }
}
