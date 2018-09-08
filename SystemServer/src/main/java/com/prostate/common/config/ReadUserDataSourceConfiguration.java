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
@MapperScan(basePackages = "com.prostate.user.mapper.slaver", sqlSessionTemplateRef  = "readUserSqlSessionTemplate")
public class ReadUserDataSourceConfiguration {

    @Value("${spring.datasource.readUser.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.readUser.url}")
    private String url;

    @Value("${spring.datasource.readUser.username}")
    private String username;

    @Value("${spring.datasource.readUser.password}")
    private String password;


    @Bean(name = "readUserDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "readUserSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("readUserDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/user/slaver/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "readUserTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("readUserDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "readUserSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readUserSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}