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
@MapperScan(basePackages = "com.prostate.base.mapper.slaver", sqlSessionTemplateRef  = "readBaseSqlSessionTemplate")
public class ReadBaseDataSourceConfiguration {

    @Value("${spring.datasource.readBase.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.readBase.url}")
    private String url;

    @Value("${spring.datasource.readBase.username}")
    private String username;

    @Value("${spring.datasource.readBase.password}")
    private String password;


    @Bean(name = "readBaseDataSource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        return dataSource;
    }

    @Bean(name = "readBaseSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("readBaseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/base/slaver/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "readBaseTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("readBaseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "readBaseSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("readBaseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}