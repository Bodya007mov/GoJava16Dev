package com.movchan.hibernate.dao;

import com.movchan.hibernate.domain.Company;

public class CompanyDAO extends GenericDAO<Company, Long> {

    @Override
    public Class<Company> getEntityClass() {
        return Company.class;
    }
}
