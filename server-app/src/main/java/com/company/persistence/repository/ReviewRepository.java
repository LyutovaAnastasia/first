package com.company.persistence.repository;

import com.company.persistence.entity.ReviewEntity;
import com.company.persistence.projection.ReviewProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "select r.id, r.begin_date as beginDate, r.end_date as endDate," +
        "       r.minuses, r.pluses, r.comment, r.mentor, r.employed, r.date, r.mark, r.active, " +
        "       r.class_id as classId, r.user_id as userId, " +
        "       (SELECT username FROM server.users WHERE id = r.user_id) as username" +
        "    from server.reviews r" +
        "            left join server.classes c on r.class_id = c.id and r.active = true" +
        "     where c.id = :classId", nativeQuery = true)
    List<ReviewProjection> getsReviewsByClassId(@Param("classId") Long id);

    @Query(value = "select r.id, r.begin_date as beginDate, r.end_date as endDate," +
        "              r.minuses, r.pluses, r.comment, r.mentor, r.employed, r.date, r.mark, r.active," +
        "              r.class_id as classId, r.user_id as userId," +
        "               (SELECT username FROM server.users WHERE id = r.user_id) as username" +
        "            from server.reviews r where r.active = true", nativeQuery = true)
    List<ReviewProjection> getsReviewsActive();

    @Query(value = "select r.id, r.begin_date as beginDate, r.end_date as endDate," +
        "              r.minuses, r.pluses, r.comment, r.mentor, r.employed, r.date, r.mark, r.active," +
        "              r.class_id as classId, r.user_id as userId," +
        "               (SELECT username FROM server.users WHERE id = r.user_id) as username" +
        "            from server.reviews r where r.active = false", nativeQuery = true)
    List<ReviewProjection> getsReviewsNoActive();
}
