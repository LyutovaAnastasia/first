package com.company.domain.model.response;

import com.company.domain.model.dto.admin.AcademyAdminDto;
import com.company.domain.model.dto.admin.CategoryAdminDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryAdminResponse {

    private CategoryAdminDto category;
    private List<AcademyAdminDto> academies;
}

