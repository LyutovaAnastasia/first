package com.company.domain.model.response;

import com.company.domain.model.dto.AcademyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponse {
    private AcademyDto academy;
//    private List<ClassDto> classes;
//    private List<CategoryDto> categories;
}
