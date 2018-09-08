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
@MapperScan(basePackages = "com.prostate.base.mapper.master", sqlSessionTemplateRef  = "writeBaseSqlSessionTemplate")
public class WriteBaseDataSourceConfiguration {

    @Value("${spring.datasource.writeBase.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.writeBase.url}")
    private String url;

    @Value("${spring.datasource.writeBase.username}")
    private String username;

    @Value("${spring.datasource.writeBase.password}")
    private String password;


    @Bean(name = "writeBaseDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "writeBaseSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("writeBaseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/base/master/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "writeBaseTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("writeBaseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "writeBaseSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("writeBaseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}