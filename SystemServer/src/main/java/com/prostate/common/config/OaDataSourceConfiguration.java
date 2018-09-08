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
 * 读操作数据源
 */
@Configuration
@MapperScan(basePackages = "com.prostate.oa.dao", sqlSessionTemplateRef  = "oaSqlSessionTemplate")
public class OaDataSourceConfiguration {

    @Value("${spring.datasource.oa.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.oa.url}")
    private String url;

    @Value("${spring.datasource.oa.username}")
    private String username;

    @Value("${spring.datasource.oa.password}")
    private String password;


    @Bean(name = "oaDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "oaSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("oaDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/oa/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "oaTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("oaDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oaSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("oaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}