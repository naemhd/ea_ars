package edu.miu.cs.cs544.ea_ars.dto.adapter;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

public class ModelMapperConfiguration {
    public static ModelMapper getConfiguration(ModelMapper modelMapper){
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return modelMapper;
    }
}
