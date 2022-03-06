package com.company.domain.service.impl;

import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.dto.SectionDto;
import com.company.domain.model.response.SectionResponse;
import com.company.domain.service.SectionService;
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
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

//    @Override
//    public <D, T> D map(T entity, Class<D> outClass) {
//        return mapper.map(entity, outClass);
//    }
//
//    @Override
//    public SectionResponse getCategoryResponse(SectionEntity sectionEntity) {
//        List<CategoryEntity> categoryEntityList = sectionEntity.getCategories();
//
//        SectionDto sectionDto = mapper.map(sectionEntity, SectionDto.class);
//
//        List<CategoryDto> categoryDtoList = categoryEntityList.stream()
//            .map(entity -> map(entity, CategoryDto.class))
//            .collect(Collectors.toList());
//
//        return SectionResponse.builder()
//            .section(sectionDto)
//            .categories(categoryDtoList)
//            .build();
//    }

    @Override
    public SectionResponse getSection(Long id) {
        var sectionEntity = sectionRepository.findById(id).orElseThrow(
            () -> new RuntimeException("section not found"));
        return mapper.map(sectionEntity, SectionResponse.class);
    }

    @Override
    public List<SectionResponse> getAll() {
        var listSectionEntity = sectionRepository.findAll();
        List<SectionResponse> result = listSectionEntity.stream()
            .map(e -> mapper.map(e, SectionResponse.class))
            .collect(Collectors.toList());

//        List<SectionResponse> categoryResponseList = new ArrayList<>();
//
//        for (SectionEntity sectionEntity : listSectionEntity) {
//            categoryResponseList.add(getCategoryResponse(sectionEntity));
//        }

        return result;

    }
}
