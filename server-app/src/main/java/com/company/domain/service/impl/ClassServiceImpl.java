package com.company.domain.service.impl;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.ClassDtoHeader;
import com.company.domain.service.ClassService;
import com.company.persistence.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final ModelMapper mapper;

    @Override
    @Transactional
    public ClassDtoHeader getClass(Long id) {
        var ctx = classRepository.getClassById(id);
        return mapper.map(ctx, ClassDtoHeader.class);
    }
}
