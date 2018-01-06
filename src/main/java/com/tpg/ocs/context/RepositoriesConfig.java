package com.tpg.ocs.context;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.tpg.ocs.persistence.repositories"})
@EntityScan(basePackages="com.tpg.ocs.persistence.entities")
public class RepositoriesConfig {
}
