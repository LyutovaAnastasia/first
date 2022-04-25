package com.company.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademyRequest {

    private String name;
    private String linkTag;
    private String iconTag;
    private Set<Long> categories;
}
