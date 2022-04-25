package com.company.domain.service.impl;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.ClassDtoHeader;
import com.company.domain.model.request.AcademyRequest;
import com.company.domain.model.response.ClassResponse;
import com.company.domain.service.AcademyService;
import com.company.domain.service.CategoryService;
import com.company.domain.service.ImageCompanyService;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.projection.AcademyProjection;
import com.company.persistence.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2(topic = "ACADEMY-SERVICE")
@Service
@RequiredArgsConstructor
public class AcademyServiceImpl implements AcademyService {

    private final AcademyRepository academyRepository;
    private final ImageCompanyService imageCompanyService;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public AcademyDto getAcademy(Long id) {
        return Optional.ofNullable(academyRepository.getAcademyById(id))
            .map(e -> mapper.map(e, AcademyDto.class))
            .orElseThrow(() -> new EntityNotFoundException("id " + id));

//
//        var academy = academyRepository.getAcademyById(id);
//
//        if (academy == null) {
//            log.error("Unable to find entity with id " + id);
//            throw new EntityNotFoundException(id.toString());
//        }
//        else {
//            return mapper.map(academy, AcademyDto.class);
//        }
    }

    @Override
    public List<AcademyDto> getAll() {
        return academyRepository.findAll().stream()
            .map(e -> mapper.map(e, AcademyDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Long addAcademy(AcademyRequest academyRequest) {
        var image = imageCompanyService.getImageCompany(academyRequest.getIconTag());
        if (image ==  null) {
            image = imageCompanyService.addImageCompany(academyRequest.getIconTag());
        }
        var source = mapper.map(academyRequest, AcademyEntity.class);
        source.setImageId(image.getId());
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
}
