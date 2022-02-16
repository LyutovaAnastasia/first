package com.company.persistence.repository;

import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.projection.AcademyProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<AcademyEntity, Long> {

    //    @Query(name = "User.findBySpringDataNamedQuery", nativeQuery = true)
    @Query(value = "select a.id,\n" +
            "       a.name,\n" +
            "       a.link_tag as linkTag,\n" +
            "       a.icon_tag as iconTag,\n" +
            "       (select array_to_string(array_agg(cl.id), ',', '*') from server.classes cl where a.id = cl.academy_id) classes,\n" +
            "       (select array_to_string(array_agg(server_categories.id), ',', '*')\n" +
            "        from server.academies as sa\n" +
            "                 left join server.categories_academies ca on sa.id = ca.academies_id\n" +
            "                 left join server.categories server_categories on server_categories.id = ca.categories_id\n" +
            "        where sa.id = a.id\n" +
            "        group by sa.id\n" +
            "       ) as                                                                                                   categories\n" +
            "from server.academies a\n" +
            "where a.id = :academyId", nativeQuery = true)
//    @Query(nativeQuery = true)
    AcademyProjection getAcademy(@Param("academyId") Long id);
}
