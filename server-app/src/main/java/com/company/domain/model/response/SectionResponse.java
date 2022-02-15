package com.company.domain.model.response;

import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.dto.SectionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionResponse {
    private SectionDto section;
    private List<CategoryDto> categories;
}
