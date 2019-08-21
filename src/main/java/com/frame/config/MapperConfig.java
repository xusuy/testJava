package com.frame.config;

import com.frame.entity.User;
import com.frame.model.UserModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapperBean() {
        ModelMapper modelMapper = new ModelMapper();

        configure(modelMapper);

        return modelMapper;
    }

    private void configure(ModelMapper modelMapper) {
        modelMapper.createTypeMap(UserModel.class, User.class)
                .addMapping(UserModel::getAccount, User::setName);

    }
}
