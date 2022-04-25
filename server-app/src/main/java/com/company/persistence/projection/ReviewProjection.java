package com.company.persistence.projection;

import java.util.Date;

public interface ReviewProjection {

    Long getId();
    Date getBeginDate();
    Date getEndDate();
    String getMinuses();
    String getPluses();
    String getComment();
    Boolean getMentor();
    Boolean getEmployed();
    Date getDate();
    Integer getMark();
    Boolean getActive();

    Long getClassId();
    Long getUserId();
    String getUsername();

}
