package com.company.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest {

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
    private Long userId;
}
