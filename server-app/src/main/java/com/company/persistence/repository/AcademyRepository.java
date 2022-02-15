package com.company.persistence.repository;

import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<AcademyEntity, Long> {
}
