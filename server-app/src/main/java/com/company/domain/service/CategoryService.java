package com.company.domain.service;

import com.company.domain.model.dto.admin.CategoryAdminDto;
import com.company.domain.model.response.CategoryAdminResponse;
import com.company.domain.model.response.CategoryResponse;
import com.company.persistence.entity.CategoryEntity;

import java.util.List;


public interface CategoryService {

//    CategoryDto findCategoryById(Long id);

    CategoryResponse getCategory(Long id);
    List<CategoryAdminDto> getAll();

    void updateCategoryAfterInsertClass(Long id);

    List<CategoryAdminResponse> getAllResponse();

    CategoryEntity findById(Long id);
}
