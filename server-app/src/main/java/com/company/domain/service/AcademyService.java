package com.company.domain.service;

import com.company.domain.model.dto.AcademyDto;

import com.company.domain.model.dto.admin.AcademyAdminDto;
import com.company.domain.model.request.AcademyRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AcademyService {
    AcademyDto getAcademy(Long id);
    List<AcademyAdminDto> getAll();
    Long addAcademy(AcademyRequest academyRequest);
    void updateAcademy(AcademyDto academy);
    void deleteAcademy(Long id);

    Page<AcademyDto> getAllPage(Long id, Pageable pageable);
}
