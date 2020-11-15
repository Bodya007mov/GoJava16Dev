package com.movchan.hibernate.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {

    private Long id;

    private String name;

    private String phone;

    public CustomerDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
