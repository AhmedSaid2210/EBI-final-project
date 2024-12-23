package com.ebi.employee.employee.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBean {
    @Bean
    public ModelMapper getmodelMapper() {

        return new ModelMapper();
    }
}
