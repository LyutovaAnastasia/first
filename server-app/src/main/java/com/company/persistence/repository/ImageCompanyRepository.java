package com.company.persistence.repository;

import com.company.persistence.entity.ImageCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageCompanyRepository extends JpaRepository<ImageCompanyEntity, Long> {

    ImageCompanyEntity findByImage(String image);
}
