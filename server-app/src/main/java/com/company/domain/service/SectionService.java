package com.company.domain.service;

import com.company.domain.model.response.SectionResponse;
import java.util.List;

public interface SectionService {

    SectionResponse getSection(Long id);
    List<SectionResponse> getAll();
}
