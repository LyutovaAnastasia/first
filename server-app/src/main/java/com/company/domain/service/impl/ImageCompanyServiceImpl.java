package com.company.domain.service.impl;

import com.company.domain.service.ImageCompanyService;
import com.company.persistence.entity.ImageCompanyEntity;
import com.company.persistence.repository.ImageCompanyRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ImageCompanyServiceImpl implements ImageCompanyService {

    private final ImageCompanyRepository imageCompanyRepository;

    @Override
    public ImageCompanyEntity getImageCompany(String image) {
        return imageCompanyRepository.findByImage(image);
    }

    @Override
    public ImageCompanyEntity addImageCompany(String image) {
        var result = imageCompanyRepository.save(new ImageCompanyEntity(image));
        log.info("Create new image");
        return result;
    }

}
