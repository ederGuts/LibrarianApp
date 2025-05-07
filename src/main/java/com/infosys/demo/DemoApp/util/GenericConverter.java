package com.infosys.demo.DemoApp.util;

import org.modelmapper.ModelMapper;

public class GenericConverter<E,D> {

    ModelMapper modelMapper = new ModelMapper();

    public D toResponseDto(E entity,Class<D> dto) {
        return modelMapper.map(entity,dto);
    }


}
