package com.company.persistence.repository;

import com.company.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByOrderByIdAsc();

    @Modifying
    @Query(value = "UPDATE server.categories c SET" +
        "        count_of_classes = (select count(*)" +
        "    from server.classes cl where cl.category_id = c.id)" +
        "    where c.id = :id", nativeQuery = true)
    void updateCountOfClasses(@Param("id") Long id);
}
