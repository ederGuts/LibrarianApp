package com.infosys.demo.DemoApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Component;

@Slf4j
public class BaseController<E,D> {

    private static ModelMapper modelMapper = new ModelMapper();

    public D toDto(E entity,Class<D> dto) {
        this.modelMapper.getTypeMaps().stream().forEach(typeMap -> {
            log.info(typeMap.toString());
        });
        //TypeMap<E,D> typeMap = (TypeMap<E, D>) modelMapper.getTypeMap(entity.getClass(), dto);
        //if(typeMap == null ) {
            return modelMapper.map(entity, dto);
        //}

        //return typeMap.map(entity,dto);
    }

    public TypeMap<E,D> getPropertyMapper(Class<E> entityClass,Class<D> dtoClass) {
        TypeMap<E,D> propertyMapper = this.modelMapper.getTypeMap(entityClass,dtoClass);
        if(propertyMapper == null) {
            propertyMapper = this.modelMapper.createTypeMap(entityClass, dtoClass);
        }
        return propertyMapper;
    }

    public E toEntity(D dto,Class<E> entity) {
        return modelMapper.map(dto, entity);
    }

}
