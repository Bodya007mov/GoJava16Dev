package com.movchan.hibernate.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LanguageDTO {

    private Long id;

    private String name;

    public LanguageDTO(String name) {
        this.name = name;
    }
}
