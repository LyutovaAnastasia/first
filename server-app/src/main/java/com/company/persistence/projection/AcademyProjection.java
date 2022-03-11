package com.company.persistence.projection;

import javax.persistence.Transient;

public interface AcademyProjection {
    Long getId();
    String getName();
    String getLinkTag();
    String getIconTag();
    String getClasses();
}
