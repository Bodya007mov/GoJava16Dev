package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    private String name;

    private String phone;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
