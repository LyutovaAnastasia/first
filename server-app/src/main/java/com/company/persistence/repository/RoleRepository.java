package com.company.persistence.repository;

import com.company.domain.model.enums.RoleEnum;
import com.company.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEnum name);
}
