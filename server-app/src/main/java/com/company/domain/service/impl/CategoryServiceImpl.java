package com.company.domain.service.impl;

import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.response.CategoryResponse;
import com.company.domain.service.CategoryService;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

//    @Override
//    public CategoryDto findCategoryById(Long id) {
//        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(
//                ()->new RuntimeException("category not found"));
//
//        return mapper.map(categoryEntity, CategoryDto.class);
//    }

    @Override
    public CategoryResponse getCategory(Long id) {
        var source= categoryRepository.getById(id);
        return mapper.map(source, CategoryResponse.class);
    }
}
