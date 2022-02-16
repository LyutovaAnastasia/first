package com.company.mapper.config;

import com.company.domain.model.dto.AcademyDto;
import com.company.persistence.entity.AcademyEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        mapper
                .typeMap(AcademyEntity.class, AcademyDto.class)
                .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
                .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
                .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
                .addMappings(m -> m.map(AcademyEntity::getIconTag, AcademyDto::setIconTag))
                .addMappings(m -> m.map(AcademyEntity::getClasses, AcademyDto::setClasses))
                .addMappings(m -> m.map(AcademyEntity::getCategories, AcademyDto::setCategories));

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
}
