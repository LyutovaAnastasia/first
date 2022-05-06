package com.company.config;

import com.company.domain.model.dto.*;
import com.company.domain.model.request.AcademyRequest;
import com.company.domain.model.request.ClassRequest;
import com.company.domain.model.request.ReviewRequest;
import com.company.domain.model.response.CategoryAdminResponse;
import com.company.domain.model.response.CategoryResponse;
import com.company.domain.model.response.SectionResponse;
import com.company.persistence.entity.*;
import com.company.persistence.projection.ClassProjection;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapperConfigTest {
    @InjectMocks
    ModelMapper mapper;
    private EasyRandom generator;

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        var parameters = new EasyRandomParameters();
        parameters.excludeField(FieldPredicates.named("academyEntitySet").and(FieldPredicates.inClass(CategoryEntity.class)));
        parameters.excludeField(FieldPredicates.named("classes").and(FieldPredicates.inClass(AcademyEntity.class)));
        parameters.excludeField(FieldPredicates.named("categoryEntitySet").and(FieldPredicates.inClass(AcademyEntity.class)));
        generator = new EasyRandom(parameters);
    }

    @Test
    void sectionEntityToSectionResponse() {
        var from = generator.nextObject(SectionEntity.class);
        var to = mapper.map(from, SectionResponse.class);

        assertEquals(from.getId(), to.getSection().getId());
        assertEquals(from.getName(), to.getSection().getName());
        assertNotNull(to.getCategories());
    }

    @Test
    void categoryEntityToCategoryResponse() {
        var from = generator.nextObject(CategoryEntity.class);
        var to = mapper.map(from, CategoryResponse.class);

        assertEquals(from.getId(), to.getCategory().getId());
        assertEquals(from.getName(), to.getCategory().getName());
        assertEquals(from.getCountOfClasses(), to.getCategory().getCountOfClasses());
        assertNotNull(to.getClasses());
    }

    @Test
    void categoryEntityToCategoryDto() {
        var from = generator.nextObject(CategoryEntity.class);
        var to = mapper.map(from, CategoryDto.class);

        assertEquals(from.getId(), to.getId());
        assertEquals(from.getName(), to.getName());
        assertEquals(from.getCountOfClasses(), to.getCountOfClasses());
    }

    @Test
    void categoryEntityToCategoryAdminResponse() {
        var from = generator.nextObject(CategoryEntity.class);
        var to = mapper.map(from, CategoryAdminResponse.class);

        assertEquals(from.getId(), to.getCategory().getId());
        assertEquals(from.getName(), to.getCategory().getName());
        //assertNotNull(to.getAcademies());
    }

    @Test
    void classEntityToClassDto() {
        var from = generator.nextObject(ClassEntity.class);
        var to = mapper.map(from, ClassDto.class);

        assertEquals(from.getId(), to.getId());
        assertEquals(from.getName(), to.getName());
        assertEquals(from.getTerm(), to.getTerm());
        assertEquals(from.getPrice(), to.getPrice());
        assertEquals(from.getRating(), to.getRating());
        assertEquals(from.getCountOfReviews(), to.getCountOfReviews());
    }

    @Test
    void academyEntityToAcademyDto() {
        var from = generator.nextObject(AcademyEntity.class);
        var to = mapper.map(from, AcademyDto.class);


        assertEquals(from.getId(), to.getId());
        assertEquals(from.getName(), to.getName());
        assertEquals(from.getLinkTag(), to.getLinkTag());
    }

    @Test
    void academyRequestToAcademyEntity() {
        var from = generator.nextObject(AcademyRequest.class);
        var to = mapper.map(from, AcademyEntity.class);


        assertEquals(from.getName(), to.getName());
        assertEquals(from.getLinkTag(), to.getLinkTag());
        assertNotNull(to.getCategories());
    }

//    @Test
//    void reviewRequestToReviewEntity() {
//        var from = generator.nextObject(ReviewRequest.class);
//
//        mapper.createTypeMap(ReviewRequest.class, ReviewEntity.class)
//            .addMapping(ReviewRequest::getClassId, ReviewEntity::setClassId);
//
//        mapper.createTypeMap(ReviewRequest.class, ReviewEntity.class)
//            .addMapping(ReviewRequest::getUserId, ReviewEntity::setUserId);
//
//        var to = mapper.map(from, ReviewEntity.class);
//
//        assertEquals(from.getBeginDate(), to.getBeginDate());
//        assertEquals(from.getEndDate(), to.getEndDate());
//        assertEquals(from.getMinuses(), to.getMinuses());
//        assertEquals(from.getPluses(), to.getPluses());
//        assertEquals(from.getComment(), to.getComment());
//        assertEquals(from.getMentor(), to.getMentor());
//        assertEquals(from.getEmployed(), to.getEmployed());
//        assertEquals(from.getDate(), to.getDate());
//        assertEquals(from.getClassId(), to.getClassId());
//        assertEquals(from.getUserId(), to.getUserId());
//    }
}
