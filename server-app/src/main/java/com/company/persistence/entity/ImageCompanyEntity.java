package com.company.persistence.entity;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images_company")
public class ImageCompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageCompanySeqGenerator")
    @SequenceGenerator(name = "imageCompanySeqGenerator", sequenceName = "images_company_seq", allocationSize = 1)
    private Long id;
    private String image;

    public ImageCompanyEntity(String image) {
        this.image = image;
    }
}
