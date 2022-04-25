package com.company.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sections")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class SectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectionsSeqGenerator")
    @SequenceGenerator(name = "sectionsSeqGenerator", sequenceName = "section_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    @OrderBy("id ASC")
    private List<CategoryEntity> categories;
}
