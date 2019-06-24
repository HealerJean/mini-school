package com.hlj.proj.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.hlj.proj.data.dao.mybatis.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


/**
 * @author HealerJean
 * @version 1.0v
 * @ClassName DatasourceOneData
 * @date 2019/6/13  19:09.
 * @Description
 */
@Configuration
//jpa 扫描配置
//第一种
@EnableJpaRepositories(basePackages = {"com.hlj.proj.data.dao.db"})
@EntityScan(basePackages = {"com.hlj.proj.data.pojo"})

@PropertySource("classpath:db.properties")
public class DatasourceConfig {

    @Value("${hlj.datasource.url}")
    private String admoreUrl;
    @Value("${hlj.datasource.username}")
    private String admoreUsername;
    @Value("${hlj.datasource.password}")
    private String admorePassword;


    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;

    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(admoreUrl);
        druidDataSource.setUsername(admoreUsername);
        druidDataSource.setPassword(admorePassword);
        druidDataSource.setMaxActive(150);
        druidDataSource.setInitialSize(10);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setMaxWait(3000);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);

        return druidDataSource;
    }



    /**
     * 整合myBatis
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(mapperLocation));
        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        return sessionFactory.getObject();
    }

    /**
     * 初始化BaseDao
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public BaseDao initBaseDao(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        BaseDao baseDao = new BaseDao();
        baseDao.setBatchSqlSessionFactory(sqlSessionFactory);
        baseDao.setSqlSessionFactory(sqlSessionFactory);
        return baseDao;
    }

}

