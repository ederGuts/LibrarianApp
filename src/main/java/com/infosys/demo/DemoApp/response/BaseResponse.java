package com.infosys.demo.DemoApp.response;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.infosys.demo.DemoApp.entity.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<S,T> {
    
    public ModelMapper modelMapper = new ModelMapper();

    protected T toDTO(S source, Class<T> targetClass ) {
        return modelMapper.map(source,targetClass);
    }    

}
