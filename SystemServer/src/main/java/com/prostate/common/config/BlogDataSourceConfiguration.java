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
@MapperScan(basePackages = "com.prostate.blog.dao", sqlSessionTemplateRef  = "blogSqlSessionTemplate")
public class BlogDataSourceConfiguration {

    @Value("${spring.datasource.blog.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.blog.url}")
    private String url;

    @Value("${spring.datasource.blog.username}")
    private String username;

    @Value("${spring.datasource.blog.password}")
    private String password;


    @Bean(name = "blogDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "blogSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("blogDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/blog/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "blogTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("blogDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "blogSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("blogSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}