package com.company.domain.model.response;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.dto.ClassDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private CategoryDto category;
//    private List<AcademyDto> academies; // проекция
    private Set<ClassDto> classes;
}
