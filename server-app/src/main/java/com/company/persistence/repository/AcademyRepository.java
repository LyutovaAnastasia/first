package com.company.persistence.repository;

import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.projection.AcademyProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademyRepository extends JpaRepository<AcademyEntity, Long> {

    //    @Query(name = "User.findBySpringDataNamedQuery", nativeQuery = true)
    @Query(value = "select a.id, a.name, a.link_tag as linkTag, a.icon_tag as iconTag," +
        "      (select array_to_string(array_agg(cl.id), ',', '*')" +
        "      from server.classes cl where a.id = cl.academy_id) classes" +
        "            from server.academies a" +
        "            where a.id = :academyId", nativeQuery = true)
    AcademyProjection getAcademyById(@Param("academyId") Long id);

    @Query(value = "select a.id, a.name, a.link_tag as linkTag, a.icon_tag as iconTag,  (select array_to_string(array_agg(cl.id), ',', '*')" +
        "from server.classes cl where a.id = cl.academy_id) classes" +
        "        from server.academies a" +
        "                 left join server.categories_academies ca on a.id = ca.academies_id" +
        "                 left join server.categories c on ca.categories_id = c.id" +
        "        where c.id = :categoryId", nativeQuery = true)
    Page<AcademyProjection> getAcademiesByCategoryId(@Param("categoryId") Long id, Pageable pageable);
}
