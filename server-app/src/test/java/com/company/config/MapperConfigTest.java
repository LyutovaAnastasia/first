package com.company.config;

import com.company.model.dto.AcademyDto;
import com.company.persistence.entity.AcademyEntity;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MapperConfigTest {
    @InjectMocks
    ModelMapper mapper;
    private EasyRandom generator;

//    @BeforeAll
//    public static void init() {
//        generator = new EasyRandom();
//    }

    @BeforeEach
    public void before() {
        MockitoAnnotations.openMocks(this);
        generator = new EasyRandom();
    }

//    @Test
//    void sectionEntityToSectionDto() {
//        var from = generator.nextObject(SectionEntity.class);
//        var to = mapper.map(from, SectionDto.class);
//
//        Assertions.assertEquals(from.getId(), to.getId());
//        Assertions.assertEquals(from.getName(), to.getName());
//    }

//    @Test
//    void academyEntityToAcademyDto() {
//        var from = generator.nextObject(AcademyEntity.class);
//        var to = mapper.map(from, AcademyDto.class);
//
//        assertEquals(from.getId(), to.getId());
//        assertEquals(from.getName(), to.getName());
//        assertEquals(from.getLinkTag(), to.getLinkTag());
//        assertEquals(from.getIconTag(), to.getIconTag());
//    }
//
//    @Test
//    void classEntityToClassDto() {
//        var from = generator.nextObject(ClassEntity.class);
//        var to = mapper.map(from, ClassDto.class);
//
//        Assertions.assertEquals(from.getId(), to.getId());
//        Assertions.assertEquals(from.getName(), to.getName());
//        Assertions.assertEquals(from.getTerm(), to.getTerm());
//        Assertions.assertEquals(from.getPrice(), to.getPrice());
//        Assertions.assertEquals(from.getRating(), to.getRating());
//    }

}