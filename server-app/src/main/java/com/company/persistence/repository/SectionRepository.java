package com.company.persistence.repository;

import com.company.persistence.entity.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface SectionRepository extends JpaRepository<SectionEntity, Long> {
}
