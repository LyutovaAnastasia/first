package com.company.domain.model.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder (builderMethodName = "ClassDtoBuilder")
public class ClassDtoHeader extends ClassDto {

    private String description;
    private String linkTag;
    private String iconTag;
}
