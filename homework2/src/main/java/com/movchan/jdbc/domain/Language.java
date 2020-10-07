package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    private Long id;

    private String name;

    public Language(String name) {
        this.name = name;
    }
}
