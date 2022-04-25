package com.company.domain.service;

import com.company.domain.model.dto.ClassDto;
import com.company.domain.model.request.ClassRequest;
import com.company.domain.model.response.ClassResponse;
import com.company.persistence.entity.ClassEntity;

import java.util.List;

public interface ClassService {
    Long addClass(ClassRequest classRequest);
    ClassResponse getClass(Long id);
    List<ClassDto> getAll();
    void updateClassAfterInsertReview(Long id);
    ClassEntity findById(Long id);
}
