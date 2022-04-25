package com.company.persistence.projection;

public interface ClassProjection {
    Long getId();
    String getName();
    Integer getTerm();
    Integer getPrice();
    Integer getRating();
    Integer getCountOfReviews();
    String getDescription();
    String getLinkTag();
    String getIconTag();
}
