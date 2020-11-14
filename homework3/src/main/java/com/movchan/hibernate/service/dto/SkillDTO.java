package com.movchan.hibernate.service.dto;

import com.movchan.hibernate.domain.Level;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SkillDTO {

    private Long id;

    private Level level;

    private Long languageId;

    public SkillDTO(Level level, Long languageId) {
        this.level = level;
        this.languageId = languageId;
    }
}
