package com.company.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "academies")
@ToString(exclude = "categoryEntitySet")
@EqualsAndHashCode(exclude = "categoryEntitySet")
//@ToString(of = {"orderId"})
public class AcademyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "link_tag")
    private String linkTag;

    @Column(name = "icon_tag")
    private String iconTag;

    @OneToMany(mappedBy = "academy")
    private List<ClassEntity> classes;

    @ManyToMany(mappedBy = "academyEntitySet")
    private Set<CategoryEntity> categoryEntitySet;
}
