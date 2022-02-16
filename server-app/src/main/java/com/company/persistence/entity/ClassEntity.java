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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "academy_id")
    private AcademyEntity academy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private Integer rating;
}
