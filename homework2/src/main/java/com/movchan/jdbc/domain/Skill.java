package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill {

    public Skill(Level level, Long languageId) {
        this.level = level;
        this.languageId = languageId;
    }

    private Long id;

    private Level level;

    private Long languageId;
}
