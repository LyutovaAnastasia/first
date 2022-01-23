package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "term")
    private Integer term;

    @Column(name = "price")
    private Integer price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "academy_id")
    private AcademyEntity academy;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "rating")
    private Integer rating;
}
