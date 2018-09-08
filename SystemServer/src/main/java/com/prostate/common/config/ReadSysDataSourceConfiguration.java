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
@MapperScan(basePackages = "com.prostate.system.mapper.slaver", sqlSessionTemplateRef  = "readSysSqlSessionTemplate")
public class ReadSysDataSourceConfiguration {

    @Value("${spring.datasource.readSys.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.readSys.url}")
    private String url;

    @Value("${spring.datasource.readSys.username}")
    private String username;

    @Value("${spring.datasource.readSys.password}")
    private String password;


    @Bean(name = "readSysDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "readSysSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("readSysDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/system/slaver/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "readSysTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("readSysDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "readSysSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readSysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}