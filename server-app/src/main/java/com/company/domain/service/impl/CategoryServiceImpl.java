package com.company.domain.service.impl;

import com.company.domain.model.dto.admin.CategoryAdminDto;
import com.company.domain.model.response.CategoryAdminResponse;
import com.company.domain.model.response.CategoryResponse;
import com.company.domain.model.response.SectionResponse;
import com.company.domain.service.CategoryService;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.entity.ClassEntity;
import com.company.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2(topic = "CATEGORY-SERVICE")
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public CategoryResponse getCategory(Long id) {
        var source= categoryRepository.findById(id).orElse(null);
        if (source == null) {
            log.error("Unable to find entity with id " + id);
            throw new EntityNotFoundException("id " + id);
        }
        else {
            return mapper.map(source, CategoryResponse.class);
        }
    }

    @Override
    public List<CategoryAdminDto> getAll() {
        var source= categoryRepository.findAll();
        return source.stream().map(e -> mapper.map(e, CategoryAdminDto.class)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateCategoryAfterInsertClass(Long id) {
        categoryRepository.updateCountOfClasses(id);
    }

    @Override
    public List<CategoryAdminResponse> getAllResponse() {
        var source = categoryRepository.findAllByOrderByIdAsc();
        var result = source.stream().map(e -> mapper.map(e, CategoryAdminResponse.class))
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public CategoryEntity findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException(id.toString()));
    }
}
