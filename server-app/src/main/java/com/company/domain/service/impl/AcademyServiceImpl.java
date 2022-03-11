package com.company.domain.service.impl;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.service.AcademyService;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.projection.AcademyProjection;
import com.company.persistence.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public AcademyDto getAcademy(Long id) {
        var academy = academyRepository.getAcademyById(id);
        var result = mapper.map(academy, AcademyDto.class);
        return result;
    }

    @Override
    public List<AcademyDto> getAll() {
        return academyRepository.findAll().stream()
            .map(e -> mapper.map(e, AcademyDto.class))
            .collect(Collectors.toList());
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
    @Transactional
    public Page<AcademyDto> getAllPage(Long id, Pageable pageable) {
         Page<AcademyProjection> page = academyRepository.getAcademiesByCategoryId(id, pageable);
         return new PageImpl<AcademyDto>(page.getContent()
             .stream()
             .map(e -> mapper.map(e, AcademyDto.class)).collect(Collectors.toList()), pageable, page.getTotalElements());

    }

    @Override
    @Transactional
    public List<AcademyDto> getAllPage1(Long id) {
        List<AcademyProjection> page = academyRepository.getAcademiesByCategoryId1(id);
        return page.stream()
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
