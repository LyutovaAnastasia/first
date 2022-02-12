package com.company.config;

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
//        mapper
//                .typeMap(AcademyEntity.class, AcademyDto.class)
//                .addMappings(m -> m.map(AcademyEntity::getId, AcademyDto::setId))
//                .addMappings(m -> m.map(AcademyEntity::getName, AcademyDto::setName))
//                .addMappings(m -> m.map(AcademyEntity::getLinkTag, AcademyDto::setLinkTag))
//                .addMappings(m -> m.map(AcademyEntity::getIconTag, AcademyDto::setIconTag));
//                .addMappings(m -> m.map(source -> source.getEmployee().getId(), OrderDto::setEmployeeId))
//                .addMappings(m -> m.map(source -> source.getClient().getId(), OrderDto::setClientId))
//                .addMappings(m -> m.map(source -> source.getItem().getItemId(), OrderDto::setItemId))
//                .addMappings(m -> m.map(OrderEntity::getCompletionDate, OrderDto::setCompletionDate));

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
