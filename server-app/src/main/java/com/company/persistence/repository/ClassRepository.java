package com.company.persistence.repository;

import com.company.persistence.entity.ClassEntity;
import com.company.persistence.projection.ClassProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long>{

    @Query(value = "select c.id, c.name, c.term, c.price, c.rating, c.count_of_reviews as countOfReviews," +
        "       c.description, c.link_tag as linkTag," +
        "       (select image from server.images_company where id = c.image_id) as iconTag " +
        "from server.classes c " +
        "where c.id = :classId", nativeQuery = true)
    ClassProjection getsClassById(@Param("classId") Long id);

    @Modifying
    @Query(value = "UPDATE server.classes c SET" +
        "       count_of_reviews = (select count(*)" +
        "           from server.reviews where class_id = c.id)," +
        "       rating = trunc((select avg(mark) from server.reviews where class_id = c.id)) " +
        "where id = :id", nativeQuery = true)
    void updateCountOfReviewsAndRating(@Param("id") Long id);

    @Query(value = "select c.id, c.name, c.term, c.price, c.rating, c.count_of_reviews as countOfReviews," +
        "       c.description, c.link_tag as linkTag," +
        "       (select image from server.images_company where id = c.image_id) as iconTag " +
        "from server.classes c ", nativeQuery = true)
    List<ClassProjection> getsClasses();
}

