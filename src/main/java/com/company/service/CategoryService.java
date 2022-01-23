package com.company.service;

import com.company.model.dto.CategoryDto;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryDto findCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
                ()->new RuntimeException("category not found"));

        return mapper.map(categoryEntity, CategoryDto.class);
    }
}
