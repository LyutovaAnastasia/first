package com.company.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

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
    private Boolean active;

    private Long classId;
    private Long userId;
    private String username;
}
