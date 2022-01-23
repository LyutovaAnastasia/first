package com.company.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString(exclude = "academyEntitySet")
@EqualsAndHashCode(exclude = "academyEntitySet")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    private Integer countOfClasses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "categories_academies",
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "academies_id")
    )
    private Set<AcademyEntity> academyEntitySet;

    @OneToMany(mappedBy = "category")
    private List<ClassEntity> classes;
}
