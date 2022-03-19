package com.company.domain.service;

import com.company.domain.model.dto.AcademyDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademyService {
    AcademyDto getAcademy(Long id);
    List<AcademyDto> getAll();
    Long addAcademy(AcademyDto academy);
    void updateAcademy(AcademyDto academy);
    void deleteAcademy(Long id);

    Page<AcademyDto> getAllPage(Long id, Pageable pageable);
}
