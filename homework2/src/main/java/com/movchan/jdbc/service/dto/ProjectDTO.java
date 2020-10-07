package com.movchan.jdbc.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ProjectDTO {

    private Date creationDate;

    private String name;

    private int developerCount;

    @Override
    public String toString() {
        return String.format("%td %<tB %<tY", creationDate) +
                " - " + name +
                " - " + developerCount;
    }
}
