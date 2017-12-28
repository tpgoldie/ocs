package com.tpg.ocs.context;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Import(PersistenceConfig.class)
@EnableJpaRepositories(basePackages = {"com.tpg.shop.persistence"})
@EntityScan(basePackages="com.tpg.shop.entities")
public class RepositoriesConfig {
}
