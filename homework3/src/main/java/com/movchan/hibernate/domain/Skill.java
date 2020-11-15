package com.movchan.hibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "skills")
@ToString(exclude = "developers")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Type(type = "com.movchan.hibernate.domain.EnumTypeDB")
    @Column(name = "level")
    private Level level;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
    private List<Developer> developers = new ArrayList<>();
}
