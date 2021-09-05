package com.jadovalf.gestionempresasback.config;

import com.googlecode.jmapper.api.JMapperAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public JMapperAPI modelJMapperApi() {
        return new JMapperAPI();
    }
}
