package com.movchan.hibernate.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyDTO {

    private Long id;

    private String name;

    private String phone;

    public CompanyDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
