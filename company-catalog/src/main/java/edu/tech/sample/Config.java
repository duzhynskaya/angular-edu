package edu.tech.sample;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    @Bean(name = "dozerBean")
    public Mapper configDozer() {
        return new DozerBeanMapper();
    }
}
