package com.company.domain.service.impl;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.response.SectionResponse;
import com.company.domain.service.SectionService;
import com.company.persistence.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2(topic = "SECTION-SERVICE")
@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

    @Override
    public SectionResponse getSection(Long id) {
        var sectionEntity = sectionRepository.findById(id).orElse(null);
        if (sectionEntity == null) {
            log.error("Unable to find entity with id " + id);
            throw new EntityNotFoundException("id " + id);
        }
        else {
            return mapper.map(sectionEntity, SectionResponse.class);
        }
    }

    @Override
    public List<SectionResponse> getAll() {
        var listSectionEntity = sectionRepository.findAll();
        List<SectionResponse> result = listSectionEntity.stream()
            .map(e -> mapper.map(e, SectionResponse.class))
            .collect(Collectors.toList());
        return result;
    }
}
