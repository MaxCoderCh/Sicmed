package com.prostate.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 */
@Configuration
@MapperScan(basePackages = "com.prostate.activiti.dao", sqlSessionTemplateRef  = "activitiSqlSessionTemplate")
public class ActivitiDataSourceConfiguration {

    @Value("${spring.datasource.activiti.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.activiti.url}")
    private String url;

    @Value("${spring.datasource.activiti.username}")
    private String username;

    @Value("${spring.datasource.activiti.password}")
    private String password;


    @Bean(name = "activitiDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "activitiSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("activitiDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/activiti/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "activitiTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("activitiDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "activitiSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("activitiSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}