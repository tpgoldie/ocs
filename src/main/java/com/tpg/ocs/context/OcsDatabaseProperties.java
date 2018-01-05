package com.tpg.ocs.context;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "jdbc")
public class OcsDatabaseProperties {
    private String url;

    private String driver;

    private String username;

    private String password;
}
