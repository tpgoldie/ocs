package com.tpg.ocs.context;

import com.tpg.ocs.client.UserAuthenticationServiceClient;
import com.tpg.ocs.client.UserAuthenticationServiceClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientsConfig {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public UserAuthenticationServiceClient userAuthenticationServiceClient() {

        return new UserAuthenticationServiceClientImpl(restTemplateBuilder);
    }
}
