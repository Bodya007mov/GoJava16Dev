package com.movchan.hibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Type(type = "com.movchan.hibernate.domain.EnumTypeDB")
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "salary")
    private BigDecimal salary;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Skill> skills = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_projects",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Project> projects = new ArrayList<>();
}
