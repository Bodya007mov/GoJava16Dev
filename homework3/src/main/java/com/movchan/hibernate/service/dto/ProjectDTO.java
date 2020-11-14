package com.movchan.hibernate.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProjectDTO {

    private Long id;

    private String name;

    private Date creationDate;

    private Long companyId;

    private Long customerId;

    private BigDecimal cost;

    public ProjectDTO(String name, Date creationDate, Long companyId, Long customerId, BigDecimal cost) {
        this.name = name;
        this.creationDate = creationDate;
        this.companyId = companyId;
        this.customerId = customerId;
        this.cost = cost;
    }
}
