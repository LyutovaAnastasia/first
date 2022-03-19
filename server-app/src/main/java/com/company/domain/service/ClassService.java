package com.company.domain.service;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.ClassDtoHeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    ClassDtoHeader getClass(Long id);

}
