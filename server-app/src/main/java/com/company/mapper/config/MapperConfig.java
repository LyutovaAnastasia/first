package com.company.mapper.config;

import com.company.domain.model.dto.AcademyDto;
import com.company.domain.model.dto.CategoryDto;
import com.company.domain.model.dto.ClassDto;
import com.company.domain.model.response.CategoryResponse;
import com.company.domain.model.response.SectionResponse;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.entity.CategoryEntity;
import com.company.persistence.entity.ClassEntity;
import com.company.persistence.entity.SectionEntity;
import com.company.persistence.projection.AcademyProjection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class MapperConfig {

    @Bean
    public ModelMapper createMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper
            .getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(false)
            .setSkipNullEnabled(true)
            .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        mapper
            .typeMap(SectionEntity.class, SectionResponse.class)
            .addMappings(m -> m.<Long>map(SectionEntity::getId, (target, value) -> target.getSection().setId(value)))
            .addMappings(m -> m.<String>map(SectionEntity::getName, (target, value) -> target.getSection().setName(value)))
            .addMappings(m -> m.map(SectionEntity::getCategories, SectionResponse::setCategories));


        mapper
            .typeMap(CategoryEntity.class, CategoryResponse.class)
            .addMappings(m -> m.<Long>map(CategoryEntity::getId, (target, value) -> target.getCategory().setId(value)))
            .addMappings(m -> m.<String>map(CategoryEntity::getName, (target, value) -> target.getCategory().setName(value)))
            .addMappings(m -> m.<Integer>map(CategoryEntity::getCountOfClasses, (target, value) -> target.getCategory().setCountOfClasses(value)))
//            .addMappings(m -> m.map(CategoryEntity::getAcademyEntitySet, CategoryResponse::setAcademies))
            .addMappings(m -> m.map(CategoryEntity::getClasses, CategoryResponse::setClasses));

        mapper
            .typeMap(CategoryEntity.class, CategoryDto.class)
            .addMappings(m -> m.map(CategoryEntity::getId, CategoryDto::setId))
            .addMappings(m -> m.map(CategoryEntity::getName, CategoryDto::setName))
            .addMappings(m -> m.map(CategoryEntity::getCountOfClasses, CategoryDto::setCountOfClasses));


        mapper
            .typeMap(ClassEntity.class, ClassDto.class)
            .addMappings(m -> m.map(ClassEntity::getId, ClassDto::setId))
            .addMappings(m -> m.map(ClassEntity::getName, ClassDto::setName))
            .addMappings(m -> m.map(ClassEntity::getTerm, ClassDto::setTerm))
            .addMappings(m -> m.map(ClassEntity::getPrice, ClassDto::setPrice))
            .addMappings(m -> m.map(ClassEntity::getRating, ClassDto::setRating));

        mapper
            .typeMap(AcademyEntity.class, AcademyDto.class)
            .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
            .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
            .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
            .addMappings(m -> m.map(AcademyEntity::getIconTag, AcademyDto::setIconTag))
            .addMappings(m -> m.skip(AcademyDto::setClasses))
            .setPostConverter(ctx -> {
                var src = ctx.getSource();
                var dst = ctx.getDestination();
                var classIds = src.getClasses().stream().map(e -> e.getId()).collect(Collectors.toSet());
                dst.setClasses(classIds);
                return dst;
            });


        mapper
            .typeMap(AcademyProjection.class, AcademyDto.class)
            .addMappings(m -> m.map(AcademyProjection::getId, AcademyDto::setId))
            .addMappings(m -> m.map(AcademyProjection::getName, AcademyDto::setName))
            .addMappings(m -> m.map(AcademyProjection::getLinkTag, AcademyDto::setLinkTag))
            .addMappings(m -> m.map(AcademyProjection::getIconTag, AcademyDto::setIconTag))
            .setPostConverter(ctx -> {
                var src = ctx.getSource();

                var dst = ctx.getDestination();

                var ingredientIds = insertDataLong(src.getClasses());
                dst.setClasses(ingredientIds);
//
//                    var toppingsIds = insertDataLong(src.getCategories());
//                    dst.setCategories(toppingsIds);

                return dst;
            });

//        mapper
//                .typeMap(AcademyEntity.class, AcademyDto.class)
//                .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
//                .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
//                .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
//                .addMappings(m -> m.map(AcademyEntity::getIconTag, AcademyDto::setIconTag));
//                .addMappings(m -> m.<List<ClassEntity>>map(source -> source.getClasses(), (target, value) -> {
//                    System.out.println();
//                    var collect = Optional.ofNullable(value).orElse(Collections.emptyList()).stream().map(e -> e.getId()).collect(Collectors.toList());
//                    target.setClasses(collect);
//                }));
//        value.stream().map(e -> e.getId()).collect(Collectors.toList())
//                .addMappings(m -> m.<List<CategoryEntity>>map(source -> source.getCategories(), (target, value) -> target.setClasses(value.stream().map(e -> e.getId()).collect(Collectors.toList()))));
//                .addMappings(m -> m.map(AcademyEntity::getClasses, AcademyDto::setClasses))
//                .addMappings(m -> m.map(AcademyEntity::getCategories, AcademyDto::setCategories));

//        mapper
//                .typeMap(GetDatabaseInfoResponse.class, GetDecksProxyResponse.class)
//                .addMappings(m -> m.using(rowConverter).map(GetDatabaseInfoResponse::getRows, GetDecksProxyResponse::setDecks));
        return mapper;

//        Converter<List<RowDto>, List<DeckProxyDto>> rowConverter = ctx -> {
//            var src = ctx.getSource();
//            var decksWithRows = src.stream()
////                .collect(Collectors.groupingBy(RowDto::getDeck));
//
//            var result = decksWithRows.keySet().stream()
//                .map(e -> {
//                    var rows = decksWithRows.get(e).stream().map(m -> mapper.map(m, RowProxyDto.class)).collect(Collectors.toList());
//                    return DeckProxyDto.builder()
////                        .name(e)
//                        .rows(rows)
//                        .build();
//                }).collect(Collectors.toList());
//            return result;
//        };


    }

    private static List<String> insertData(String source) {
        if (source == null) {
            return null;
        }
        return Arrays.stream(source.split(","))
            .filter(Predicate.not("*"::equals))
            .collect(Collectors.toList());
    }

    private static Set<Long> insertDataLong(String source) {
        if (source == null) {
            return null;
        }
        return Arrays.stream(source.split(","))
            .filter(Predicate.not("*"::equals))
            .map(Long::parseLong)
            .collect(Collectors.toSet());
    }


    public <T> List<T> magicalListGetter() {
        return new ArrayList<T>();
    }
}
