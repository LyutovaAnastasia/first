package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "classSeqGenerator")
    @SequenceGenerator(name = "classSeqGenerator", sequenceName = "class_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Integer term;
    private Integer price;
    private Integer rating;

    private Integer countOfReviews;
    private String description;

    private String linkTag;
    private Long imageId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "academy_id")
//    private AcademyEntity academy;
    private Long academyId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id")
//    private CategoryEntity category;
    private Long categoryId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId")
    private List<ReviewEntity> reviews;
}
