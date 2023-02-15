package com.training0802.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongodb")
public class MongoDatasourceConfig {
    @Bean
    public MongoDatasource mongoDatasource(){
        return new MongoDatasource("Mongodb object, Chỉ khởi tạo khi 'mongodb' profile active");
    }
}
