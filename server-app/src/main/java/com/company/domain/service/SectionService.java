package com.company.domain.service;

import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.dto.SectionDto;
import com.company.domain.model.response.SectionResponse;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.entity.SectionEntity;
import com.company.persistence.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface SectionService {

    <D, T> D map(T entity, Class<D> outClass);

    SectionResponse getCategoryResponse(SectionEntity sectionEntity);

    SectionResponse findCategoriesById(Long id);

    List<SectionResponse> getAll();
}
