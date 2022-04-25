package com.company.domain.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRequest {

    private String name;
    private Integer term;
    private Integer price;

    private String description;
    private String linkTag;
    private String iconTag;

    private Long academyId;
    private Long categoryId;
}
