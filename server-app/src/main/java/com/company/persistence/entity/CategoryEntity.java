package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

//@ToString(exclude = "academyEntitySet")
//@EqualsAndHashCode(exclude = "academyEntitySet")
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class CategoryEntity {
    //
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesSeqGenerator")
    @SequenceGenerator(name = "categoriesSeqGenerator", sequenceName = "category_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private SectionEntity section;

    private Integer countOfClasses;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_academies",
            joinColumns = @JoinColumn(name = "categories_id"),
            inverseJoinColumns = @JoinColumn(name = "academies_id")
    )
    private Set<AcademyEntity> academyEntitySet;

//    @OneToMany(mappedBy = "category")
//    private Set<CategoryAcademyEntity> academies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<ClassEntity> classes;
}