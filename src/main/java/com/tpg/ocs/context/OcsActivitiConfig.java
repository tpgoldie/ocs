package com.tpg.ocs.context;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(OcsActivitiProperties.class)
public class OcsActivitiConfig {

    @Autowired
    private OcsActivitiProperties activitiDatabaseProperties;

//    @Bean
//    public DataSource dataSource() {
//
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName(activitiDatabaseProperties.getDriver());
//
//        dataSource.setUrl(activitiDatabaseProperties.getUrl());
//
//        dataSource.setUsername(activitiDatabaseProperties.getUsername());
//
//        dataSource.setPassword(activitiDatabaseProperties.getPassword());
//
//        dataSource.setDefaultAutoCommit(activitiDatabaseProperties.isDefaultAutoCommit());
//
//        return dataSource;
//    }

//    @Bean
//    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
//
//        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
//
//        config.setDataSource(dataSource());
//
//        config.setTransactionManager(transactionManager());
//
//        return config;
//    }
//
//    @Bean
//    public ProcessEngineFactoryBean processEngineFactoryBean() {
//
//        ProcessEngineFactoryBean factoryBean = new ProcessEngineFactoryBean();
//
//        factoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
//
//        return factoryBean;
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactionManager() {
//
//        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//
//        transactionManager.setDataSource(dataSource());
//
//        return transactionManager;
//    }
}
