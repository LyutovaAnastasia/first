package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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

    private String iconTag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "academy")
    private List<ClassEntity> classes;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "academyEntitySet")
    private Set<CategoryEntity> categories;
}
