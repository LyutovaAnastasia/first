package com.company.persistence.repository;

import com.company.persistence.entity.ClassEntity;
import com.company.persistence.projection.AcademyProjection;
import com.company.persistence.projection.ClassProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

    @Query(value = "select c.id, c.name, c.term, c.price, c.rating, c.count_of_reviews as countOfReviews," +
        "       c.description, c.link_tag as linkTag," +
        "       (SELECT image FROM server.images_company WHERE id = c.image_id) as imageId " +
        "from server.classes c " +
        "where c.id = :classId", nativeQuery = true)
    ClassProjection getClassById(@Param("classId") Long id);
}

