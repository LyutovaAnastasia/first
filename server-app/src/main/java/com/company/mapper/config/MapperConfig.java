package com.company.mapper.config;

import com.company.domain.model.dto.AcademyDto;
import com.company.persistence.entity.AcademyEntity;
import com.company.persistence.projection.AcademyProjection;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

//        mapper
//                .typeMap(AcademyEntity.class, AcademyResponse.class)
//                .addMappings(m -> m.<Long>map(AcademyEntity::getId, (target, value) -> target.getAcademy().setId(value)))
//                .addMappings(m -> m.<String>map(AcademyEntity::getName, (target, value) -> target.getAcademy().setName(value)))
//                .addMappings(m -> m.<String>map(AcademyEntity::getLinkTag, (target, value) -> target.getAcademy().setLinkTag(value)))
//                .addMappings(m -> m.<String>map(AcademyEntity::getIconTag, (target, value) -> target.getAcademy().setIconTag(value)))
//
//        ;
//                .addMappings(m -> m.<BigDecimal>map(request -> request.getAddress().getCoordinates().getLatitude(), (entity, value) -> entity.getAddress().setLatitude(value)))

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

        mapper
                .typeMap(AcademyEntity.class, AcademyDto.class)
                .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
                .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
                .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
                .addMappings(m -> m.map(AcademyEntity::getIconTag, AcademyDto::setIconTag));
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

    private static List<Long> insertDataLong(String source) {
        if (source == null) {
            return null;
        }
        return Arrays.stream(source.split(","))
                .filter(Predicate.not("*"::equals))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }


    public <T> List<T> magicalListGetter() {
        return new ArrayList<T>();
    }
}
