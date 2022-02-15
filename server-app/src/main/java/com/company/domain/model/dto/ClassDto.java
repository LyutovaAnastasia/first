package com.company.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassDto {

    private Long id;
    private String name;
    private Integer term;
    private Integer price;
    private Integer rating;
}
