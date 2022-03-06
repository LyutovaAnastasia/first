package com.company.domain.service;

import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.response.CategoryResponse;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


public interface CategoryService {

//    CategoryDto findCategoryById(Long id);

    CategoryResponse getCategory(Long id);
}
