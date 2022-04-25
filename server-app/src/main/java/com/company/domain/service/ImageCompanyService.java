package com.company.domain.service;

import com.company.persistence.entity.ImageCompanyEntity;

public interface ImageCompanyService {
    ImageCompanyEntity getImageCompany(String image);
    ImageCompanyEntity addImageCompany(String image);
}
