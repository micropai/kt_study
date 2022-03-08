package com.example.springbasic.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.util.Assert;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class ModelMapperUtils {

    private final static Map<EModelMapperType ,ModelMapper> modelMappers = new HashMap<>();

    private static ModelMapper getModelMapper() {
        return getModelMapper(EModelMapperType.STANDARD);
    }

    private static ModelMapper getModelMapper(EModelMapperType mapperType) {
        if(!modelMappers.containsKey(mapperType)) {
            modelMappers.put(mapperType, getInstance(mapperType.getMatchingStrategy()));
        }
        return modelMappers.get(mapperType);
    }

    private static ModelMapper getInstance(MatchingStrategy matchingStrategy) {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(matchingStrategy)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE)
                .setSkipNullEnabled(true);
        return modelMapper;
    }

    public static <T> T map(Object source, T dest) throws MappingException {
        if(source != null) {
            getModelMapper().map(source, dest);
        }
        return dest;
    }


    public static <T> T map(Object source, Type destinationClass) throws MappingException {
        Assert.notNull(destinationClass, "destinationType");
        if(source != null) {
            return getModelMapper().map(source, destinationClass);
        }
        return null;
    }

    public static <T> T map(Object source, Type destinationClass, EModelMapperType mapperType) throws MappingException {
        Assert.notNull(mapperType, "modelMapper");
        Assert.notNull(destinationClass, "destinationType");
        if(source != null) {
            return getModelMapper(mapperType).map(source, destinationClass);
        }
        return null;
    }

    public static <T> T map(Object source, Class<T> destinationClass) throws MappingException {
        Assert.notNull(destinationClass, "destinationType");
        if(source != null) {
            return getModelMapper().map(source, destinationClass);
        }
        return null;
    }

    public static <T> T mapThrowElse(Optional<?> source, Class<T> destinationClass) throws MappingException {
        Assert.notNull(destinationClass, "destinationType");
        Assert.notNull(source, "source");
        return getModelMapper().map(source.orElseThrow(), destinationClass);
    }

    public static <T> T mapThrowElse(Optional<?> source, Class<T> destinationClass, Object id) throws MappingException {
        Assert.notNull(destinationClass, "destinationType");
        Assert.notNull(source, "source");
        return getModelMapper().map(source.orElseThrow(), destinationClass);
    }

    public static <T> T map(Object source, Class<T> destinationClass, EModelMapperType mapperType) throws MappingException {
        Assert.notNull(mapperType, "modelMapper");
        Assert.notNull(destinationClass, "destinationType");
        if(source != null) {
            return getModelMapper(mapperType).map(source, destinationClass);
        }
        return null;
    }

    /**
     * Iterable 를 해당 destinationClass 로 복사하여 list 를 생성
     * @param sources 원본 데이터
     * @param destinationClass 변경할 class
     * @return List<T>
     */
    public static <T> List<T> mapList(Iterable<?> sources, Class<T> destinationClass) {
        Assert.notNull(destinationClass, "destinationType");
        if(sources != null) {
            return StreamSupport.stream(sources.spliterator(), false).map(entity -> getModelMapper().map(entity, destinationClass))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    /**
     * Iterable 를 해당 destinationClass 로 복사하여 list 를 생성
     * @param sources 원본 데이터
     * @param destinationClass 변경할 class
     * @param mapperType mapper 사용 유형
     * @return List<T>
     */
    public static <T> List<T> mapList(Iterable<?> sources, Class<T> destinationClass, EModelMapperType mapperType) {
        Assert.notNull(mapperType, "modelMapper");
        Assert.notNull(destinationClass, "destinationType");
        if(sources != null) {
            return StreamSupport.stream(sources.spliterator(), false).map(entity -> getModelMapper(mapperType).map(entity, destinationClass))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @RequiredArgsConstructor
    @Getter
    public enum EModelMapperType {
        LOOSE(MatchingStrategies.LOOSE), STANDARD(MatchingStrategies.STANDARD), STRICT(MatchingStrategies.STRICT);
        private final MatchingStrategy matchingStrategy;
    }
}
