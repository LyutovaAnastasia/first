package com.company.domain.model.response;

import com.company.domain.model.dto.ClassDtoHeader;
import com.company.domain.model.dto.ReviewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponse {
    private ClassDtoHeader classDto;
    private List<ReviewDto> reviews;
}
