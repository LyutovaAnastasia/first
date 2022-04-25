package com.company.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademyDto {
    private Long id;
    private String name;
    private String linkTag;
    private String iconTag;
    private Set<Long> classes;
//    List<Long> categories;
}
