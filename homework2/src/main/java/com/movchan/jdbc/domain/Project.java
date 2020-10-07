package com.movchan.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private Long id;

    private String name;

    private Date creationDate;

    private Long companyId;

    private Long customerId;

    private Double cost;

    public Project(String name, Date creationDate, Long companyId, Long customerId, Double cost) {
        this.name = name;
        this.creationDate = creationDate;
        this.companyId = companyId;
        this.customerId = customerId;
        this.cost = cost;
    }
}
