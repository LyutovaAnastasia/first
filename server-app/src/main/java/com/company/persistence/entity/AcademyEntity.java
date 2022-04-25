package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academies")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class AcademyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "academiesSeqGenerator")
    @SequenceGenerator(name = "academiesSeqGenerator", sequenceName = "academy_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String linkTag;
    private Long imageId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "academyId")
    private List<ClassEntity> classes;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "academyEntitySet")
//    private Set<CategoryEntity> categories;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_academies",
            joinColumns = @JoinColumn(name = "academies_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    private Set<CategoryEntity> categories;
}
