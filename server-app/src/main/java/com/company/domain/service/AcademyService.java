package com.company.domain.service;

import com.company.domain.model.dto.AcademyDto;

import java.util.List;

public interface AcademyService {
    AcademyDto getAcademy(Long id);
    Long addAcademy(AcademyDto academy);
    void updateAcademy(AcademyDto academy);
    void deleteAcademy(Long id);
    List<AcademyDto> getAll();
}
