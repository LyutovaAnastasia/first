package com.company.service;

import com.company.model.dto.CategoryDto;
import com.company.model.dto.SectionDto;
import com.company.model.response.SectionResponse;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.entity.SectionEntity;
import com.company.persistence.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

    public <D, T> D map(T entity, Class<D> outClass) {
        return mapper.map(entity, outClass);
    }

    public SectionResponse getCategoryResponse(SectionEntity sectionEntity) {
        List<CategoryEntity> categoryEntityList = sectionEntity.getCategories();

        SectionDto sectionDto = mapper.map(sectionEntity, SectionDto.class);

        List<CategoryDto> categoryDtoList = categoryEntityList.stream()
                .map(entity -> map(entity, CategoryDto.class))
                .collect(Collectors.toList());

        return SectionResponse.builder()
                .section(sectionDto)
                .categories(categoryDtoList)
                .build();
    }

    public SectionResponse findCategoriesById(Long id) {
        SectionEntity sectionEntity = sectionRepository.findById(id).orElseThrow(
                ()->new RuntimeException("section not found"));
        return getCategoryResponse(sectionEntity);
    }

    public List<SectionResponse> findCategories() {
        var listSectionEntity = sectionRepository.findAll();
        List<SectionResponse> categoryResponseList = new ArrayList<>();

        for(SectionEntity sectionEntity : listSectionEntity) {
            categoryResponseList.add(getCategoryResponse(sectionEntity));
        }

        return categoryResponseList;
    }
}
