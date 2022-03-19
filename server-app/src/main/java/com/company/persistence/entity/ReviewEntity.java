package com.company.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewsSeqGenerator")
    @SequenceGenerator(name = "reviewsSeqGenerator", sequenceName = "review_seq", allocationSize = 1)
    private Long id;
    private Date beginDate;
    private Date endDate;
    private String minuses;
    private String pluses;
    private String comment;
    private Boolean mentor;
    private Boolean employed;
    private Date date;
    private Integer mark;

    private Long classId;
}
