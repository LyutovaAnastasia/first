package com.company.domain.model.response;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.ClassDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademyResponse {
    private AcademyDto academy;
    private List<ClassDto> classList;
}
