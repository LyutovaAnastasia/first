package com.company.service;

import com.company.model.dto.AcademyDto;
import com.company.model.dto.ClassDto;
import com.company.model.response.AcademyResponse;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.entity.ClassEntity;
import com.company.persistence.repository.CategoryRepository;
import com.company.persistence.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyService {

    private final CategoryRepository categoryRepository;
    private final ClassRepository classRepository;
    private final ModelMapper mapper;

    public <D, T> D map(T entity, Class<D> outClass) {
        return mapper.map(entity, outClass);
    }

    public List<AcademyResponse> findCategoryById(Long id) {
        return null;
//        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
//                ()->new RuntimeException("category not found"));
//        Set<AcademyEntity> academyEntitySet = categoryEntity.getAcademyEntitySet();
//
//        List<AcademyResponse> academyResponseList = new ArrayList<>();
//
//        for (AcademyEntity academyEntity : academyEntitySet) {
//            academyResponseList.add(getAcademyResponse(academyEntity, id));
//        }
//
//        return academyResponseList;
    }

    public AcademyResponse getAcademyResponse(AcademyEntity academyEntity, Long categoryId){
        AcademyDto academyDto = mapper.map(academyEntity, AcademyDto.class);

        List<ClassEntity> classEntityList = classRepository.findByCategoryIdAndAcademyId(categoryId,
                academyEntity.getId());

        List<ClassDto> classDtoList = classEntityList.stream()
                .map(entity -> map(entity, ClassDto.class))
                .collect(Collectors.toList());

        return AcademyResponse.builder()
                .academy(academyDto)
                .classList(classDtoList)
                .build();
    }

}

//
//    public SectionResponse getCategoryResponse(SectionEntity sectionEntity) {
//        List<CategoryEntity> categoryEntityList = sectionEntity.getCategories();
//
//        SectionDto sectionDto = mapper.map(sectionEntity, SectionDto.class);
//
//        List<CategoryDto> categoryDtoList = categoryEntityList.stream()
//                .map(entity -> map(entity, CategoryDto.class))
//                .collect(Collectors.toList());
//
//        return SectionResponse.builder()
//                .section(sectionDto)
//                .categories(categoryDtoList)
//                .build();
//    }
//
//    public SectionResponse findCategoriesById(Long id) {
//        SectionEntity sectionEntity = sectionRepository.findById(id).orElseThrow(
//                ()->new RuntimeException("section not found"));
//        return getCategoryResponse(sectionEntity);
//    }
