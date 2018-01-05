package com.tpg.ocs.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tpg.ocs.client.GrantedAuthorityDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;

public interface ObjectMapping {

    @Bean
    default ObjectMapper objectMapper() {

        ObjectMapper objectMapper = new ObjectMapper();

        GrantedAuthorityDeserializer deserializer = new GrantedAuthorityDeserializer();

        SimpleModule module = new SimpleModule();

        module.addDeserializer(GrantedAuthority.class, deserializer);

        objectMapper.registerModule(module);

        return objectMapper;
    }
}
