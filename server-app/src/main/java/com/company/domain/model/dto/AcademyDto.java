package com.company.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademyDto {
    private Long id;
    private String name;
    private String linkTag;
    private String iconTag;
    List<Long> classes;
//    List<Long> categories;
}
