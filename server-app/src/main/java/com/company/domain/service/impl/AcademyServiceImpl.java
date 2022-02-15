package com.company.domain.service.impl;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.service.AcademyService;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
    private final ModelMapper mapper;

    @Override
    public AcademyDto getAcademy(Long id) {
        var academy = academyRepository.getById(id);
        var result = mapper.map(academy, AcademyDto.class);
        return result;
    }

    @Override
    public Long addAcademy(AcademyDto academy) {
        var source = mapper.map(academy, AcademyEntity.class);
        var result = academyRepository.save(source);
        return result.getId();
    }

    @Override
    public void updateAcademy(AcademyDto academy) {
        var source = mapper.map(academy, AcademyEntity.class);
        academyRepository.save(source);
    }

    @Override
    public void deleteAcademy(Long id) {
        academyRepository.deleteById(id);
    }

    @Override
    public List<AcademyDto> getAll() {
        return academyRepository.findAll().stream()
                .map(e -> mapper.map(e, AcademyDto.class))
                .collect(Collectors.toList());
    }

//    public <D, T> D map(T entity, Class<D> outClass) {
//        return mapper.map(entity, outClass);
//    }
//
//    public List<AcademyResponse> findCategoryById(Long id) {
//        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
//                () -> new RuntimeException("category not found"));
//        Set<AcademyEntity> academyEntitySet = categoryEntity.getAcademyEntitySet();
//
//        List<AcademyResponse> academyResponseList = new ArrayList<>();
//
//        for (AcademyEntity academyEntity : academyEntitySet) {
//            academyResponseList.add(getAcademyResponse(academyEntity, id));
//        }
//
//        return academyResponseList;
//    }
//
//    public AcademyResponse getAcademyResponse(AcademyEntity academyEntity, Long categoryId) {
//        AcademyDto academyDto = mapper.map(academyEntity, AcademyDto.class);
//
//        List<ClassEntity> classEntityList = classRepository.findByCategoryIdAndAcademyId(categoryId,
//                academyEntity.getId());
//
//        List<ClassDto> classDtoList = classEntityList.stream()
//                .map(entity -> map(entity, ClassDto.class))
//                .collect(Collectors.toList());
//
//        return AcademyResponse.builder()
//                .academy(academyDto)
//                .classList(classDtoList)
//                .build();
//    }


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
