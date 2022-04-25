package com.company.mapper.config;

import com.company.domain.model.dto.*;
import com.company.domain.model.dto.admin.AcademyAdminDto;
import com.company.domain.model.request.AcademyRequest;
import com.company.domain.model.request.ClassRequest;
import com.company.domain.model.request.ReviewRequest;
import com.company.domain.model.response.CategoryAdminResponse;
import com.company.domain.model.response.CategoryResponse;
import com.company.domain.model.response.SectionResponse;
import com.company.persistence.entity.*;
import com.company.persistence.projection.AcademyProjection;
import com.company.persistence.projection.ClassProjection;
import com.company.persistence.projection.ReviewProjection;
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
            .typeMap(CategoryEntity.class, CategoryAdminResponse.class)
            .addMappings(m -> m.<Long>map(CategoryEntity::getId, (target, value) -> target.getCategory().setId(value)))
            .addMappings(m -> m.<String>map(CategoryEntity::getName, (target, value) -> target.getCategory().setName(value)))
            .addMappings(m -> m.map(CategoryEntity::getAcademyEntitySet, CategoryAdminResponse::setAcademies));

        mapper
            .typeMap(ClassEntity.class, ClassDto.class)
            .addMappings(m -> m.map(ClassEntity::getId, ClassDto::setId))
            .addMappings(m -> m.map(ClassEntity::getName, ClassDto::setName))
            .addMappings(m -> m.map(ClassEntity::getTerm, ClassDto::setTerm))
            .addMappings(m -> m.map(ClassEntity::getPrice, ClassDto::setPrice))
            .addMappings(m -> m.map(ClassEntity::getRating, ClassDto::setRating))
            .addMappings(m -> m.map(ClassEntity::getCountOfReviews, ClassDto::setCountOfReviews));

        mapper
            .typeMap(ClassProjection.class, ClassDtoHeader.class)
            .addMappings(m -> m.map(ClassProjection::getId, ClassDtoHeader::setId))
            .addMappings(m -> m.map(ClassProjection::getName, ClassDtoHeader::setName))
            .addMappings(m -> m.map(ClassProjection::getTerm, ClassDtoHeader::setTerm))
            .addMappings(m -> m.map(ClassProjection::getPrice, ClassDtoHeader::setPrice))
            .addMappings(m -> m.map(ClassProjection::getRating, ClassDtoHeader::setRating))
            .addMappings(m -> m.map(ClassProjection::getCountOfReviews, ClassDtoHeader::setCountOfReviews))
            .addMappings(m -> m.map(ClassProjection::getDescription, ClassDtoHeader::setDescription))
            .addMappings(m -> m.map(ClassProjection::getLinkTag, ClassDtoHeader::setLinkTag))
            .addMappings(m -> m.map(ClassProjection::getIconTag, ClassDtoHeader::setIconTag));

        mapper
            .typeMap(ClassRequest.class, ClassEntity.class)
            .addMappings(m -> m.skip(ClassEntity::setId))
            .addMappings(m -> m.map(ClassRequest::getName, ClassEntity::setName))
            .addMappings(m -> m.map(ClassRequest::getTerm, ClassEntity::setTerm))
            .addMappings(m -> m.map(ClassRequest::getPrice, ClassEntity::setPrice))
            .addMappings(m -> m.skip(ClassEntity::setRating))
            .addMappings(m -> m.skip(ClassEntity::setCountOfReviews))
            .addMappings(m -> m.map(ClassRequest::getDescription, ClassEntity::setDescription))
            .addMappings(m -> m.map(ClassRequest::getLinkTag, ClassEntity::setLinkTag))
            .addMappings(m -> m.skip(ClassEntity::setImageId))
            .addMappings(m -> m.map(ClassRequest::getAcademyId, ClassEntity::setAcademyId))
            .addMappings(m -> m.map(ClassRequest::getCategoryId, ClassEntity::setCategoryId));


        mapper
            .typeMap(AcademyEntity.class, AcademyDto.class)
            .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
            .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
            .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
            .addMappings(m -> m.skip(AcademyDto::setIconTag))
            .addMappings(m -> m.skip(AcademyDto::setClasses))
            .setPostConverter(ctx -> {
                var src = ctx.getSource();
                var dst = ctx.getDestination();
                var classIds = src.getClasses().stream().map(e -> e.getId()).collect(Collectors.toSet());
                dst.setClasses(classIds);
                return dst;
            });

        mapper
            .typeMap(AcademyRequest.class, AcademyEntity.class)
            .addMappings(m -> m.skip(AcademyEntity::setId))
            .addMappings(m -> m.map(AcademyRequest::getName, AcademyEntity::setName))
            .addMappings(m -> m.map(AcademyRequest::getLinkTag, AcademyEntity::setLinkTag))
            .addMappings(m -> m.skip(AcademyEntity::setImageId))
            .setPostConverter(ctx -> {
                var src = ctx.getSource();
                var dst = ctx.getDestination();
                var categories = src.getCategories().stream()
                    .map(CategoryEntity::new).collect(Collectors.toSet());
                dst.setCategories(categories);
                return dst;
            });

        mapper
            .typeMap(AcademyProjection.class, AcademyDto.class)
            .addMappings(m -> m.map(AcademyProjection::getId, AcademyDto::setId))
            .addMappings(m -> m.map(AcademyProjection::getName, AcademyDto::setName))
            .addMappings(m -> m.map(AcademyProjection::getLinkTag, AcademyDto::setLinkTag))
            .addMappings(m -> m.map(AcademyProjection::getImageId, AcademyDto::setIconTag))
            .setPostConverter(ctx -> {
                var src = ctx.getSource();
                var dst = ctx.getDestination();
                var ingredientIds = insertDataLong(src.getClasses());
                dst.setClasses(ingredientIds);
                return dst;
            });

//        mapper
//            .typeMap(AcademyAdminProjection.class, AcademyAdminDto.class)
//            .addMappings(m -> m.map(AcademyAdminProjection::getId, AcademyAdminDto::setId))
//            .addMappings(m -> m.map(AcademyAdminProjection::getName, AcademyAdminDto::setName))
//            .addMappings(m -> m.skip(AcademyAdminDto::setCategories))
//            .setPostConverter(ctx -> {
//                var src = ctx.getSource();
//                var dst = ctx.getDestination();
//                var ingredientIds = insertDataLong(src.getCategories());
//                dst.setCategories(ingredientIds);
//                return dst;
//            });

        mapper
            .typeMap(ReviewProjection.class, ReviewDto.class)
            .addMappings(m -> m.map(ReviewProjection::getId, ReviewDto::setId))
            .addMappings(m -> m.map(ReviewProjection::getBeginDate, ReviewDto::setBeginDate))
            .addMappings(m -> m.map(ReviewProjection::getEndDate, ReviewDto::setEndDate))
            .addMappings(m -> m.map(ReviewProjection::getMinuses, ReviewDto::setMinuses))
            .addMappings(m -> m.map(ReviewProjection::getPluses, ReviewDto::setPluses))
            .addMappings(m -> m.map(ReviewProjection::getComment, ReviewDto::setComment))
            .addMappings(m -> m.map(ReviewProjection::getMentor, ReviewDto::setMentor))
            .addMappings(m -> m.map(ReviewProjection::getEmployed, ReviewDto::setEmployed))
            .addMappings(m -> m.map(ReviewProjection::getDate, ReviewDto::setDate))
            .addMappings(m -> m.map(ReviewProjection::getMark, ReviewDto::setMark))
            .addMappings(m -> m.map(ReviewProjection::getActive, ReviewDto::setActive))
            .addMappings(m -> m.map(ReviewProjection::getClassId, ReviewDto::setClassId))
            .addMappings(m -> m.map(ReviewProjection::getUserId, ReviewDto::setUserId))
            .addMappings(m -> m.map(ReviewProjection::getUsername, ReviewDto::setUsername));

        mapper
            .typeMap(ReviewRequest.class, ReviewEntity.class)
            .addMappings(m -> m.skip(ReviewEntity::setId))
            .addMappings(m -> m.map(ReviewRequest::getBeginDate, ReviewEntity::setBeginDate))
            .addMappings(m -> m.map(ReviewRequest::getEndDate, ReviewEntity::setEndDate))
            .addMappings(m -> m.map(ReviewRequest::getMinuses, ReviewEntity::setMinuses))
            .addMappings(m -> m.map(ReviewRequest::getPluses, ReviewEntity::setPluses))
            .addMappings(m -> m.map(ReviewRequest::getComment, ReviewEntity::setComment))
            .addMappings(m -> m.map(ReviewRequest::getMentor, ReviewEntity::setMentor))
            .addMappings(m -> m.map(ReviewRequest::getEmployed, ReviewEntity::setEmployed))
            .addMappings(m -> m.map(ReviewRequest::getDate, ReviewEntity::setDate))
            .addMappings(m -> m.map(ReviewRequest::getMark, ReviewEntity::setMark))
            .addMappings(m -> m.skip(ReviewEntity::setActive))
            .addMappings(m -> m.map(ReviewRequest::getClassId, ReviewEntity::setClassId))
            .addMappings(m -> m.map(ReviewRequest::getUserId, ReviewEntity::setUserId));

        return mapper;
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
