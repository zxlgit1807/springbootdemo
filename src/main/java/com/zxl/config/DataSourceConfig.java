package com.zxl.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Auther: ZXL
 * @Date: 2018/9/14
 * @Description: 多数据源配置。这里的写法是不用包路径下对应不同的数据源
 * 主要就是数据源、factory及事物管理
 * 未完待续，有错误
 */
@Component
public class DataSourceConfig {

    /**
     * annotationClass 表示只扫描有@mapper注解接口
     */
    @Configuration
    @MapperScan(basePackages = "com.zxl.dao.mysql.one", sqlSessionFactoryRef = "oneSqlSessionFactory",
            sqlSessionTemplateRef = "oneSqlSessionTemplate", annotationClass = Mapper.class)
    public static class OneDataSourceConfig {

        /**
         * 获取配置信息
         * @return
         */
        @Bean
        @Primary
        @ConfigurationProperties("spring.datasource.one")
        public DataSourceProperties getDataSourceProperties() {
            return new DataSourceProperties();
        }

        /**
         * 使用druid，或者默认的也行
         * @return
         */
        @Bean
        @Primary
        public DataSource getDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            DataSourceProperties dataSourceProperties = getDataSourceProperties();
            dataSource.setUrl(dataSourceProperties.getUrl().trim());
            dataSource.setUsername(dataSourceProperties.getUsername().trim());
            dataSource.setPassword(dataSourceProperties.getPassword().trim());
            return dataSource;
        }

        @Bean
        @Primary
        @ConfigurationProperties(MybatisProperties.MYBATIS_PREFIX + ".one")
        public MybatisProperties getMybatisProperties() {
            return new MybatisProperties();
        }

        @Bean("oneSqlSessionFactory")
        @Primary
        public SqlSessionFactory getSqlSessionFactory() throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(getDataSource());
            // 配置mapper.xml的扫描路径
            //factoryBean.setMapperLocations(getMybatisProperties().resolveMapperLocations());
            //factoryBean.setTypeAliasesPackage(getMybatisProperties().getTypeAliasesPackage());
            return factoryBean.getObject();
        }

        @Bean("oneSqlSessionTemplate")
        @Primary
        public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
            SqlSessionTemplate template = new SqlSessionTemplate(getSqlSessionFactory());
            return template;
        }
    }

    /**
     * 第二个数据源
     */
    @Configuration
    @MapperScan(basePackages = "com.zxl.dao.mysql.two", sqlSessionFactoryRef = "twoSqlSessionFactory",
            sqlSessionTemplateRef = "twoSqlSessionTemplate", annotationClass = Mapper.class)
    public static class TwoDataSourceConfig {

        /**
         * 获取配置信息
         * @return
         */
        @Bean("twoDataSourceProperties")
        @ConfigurationProperties("spring.datasource.two")
        public DataSourceProperties getDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public DataSource getDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            DataSourceProperties dataSourceProperties = getDataSourceProperties();
            dataSource.setUrl(dataSourceProperties.getUrl().trim());
            dataSource.setUsername(dataSourceProperties.getUsername().trim());
            dataSource.setPassword(dataSourceProperties.getPassword().trim());
            return dataSource;
        }

        @Bean("twoBatisProperties")
        @ConfigurationProperties(MybatisProperties.MYBATIS_PREFIX + ".two")
        public MybatisProperties getMybatisProperties() {
            return new MybatisProperties();
        }

        @Bean("twoSqlSessionFactory")
        public SqlSessionFactory getSqlSessionFactory() throws Exception {
            SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
            factoryBean.setDataSource(getDataSource());
            // 配置mapper.xml的扫描路径
            //factoryBean.setMapperLocations(getMybatisProperties().resolveMapperLocations());
            //factoryBean.setTypeAliasesPackage(getMybatisProperties().getTypeAliasesPackage());
            return factoryBean.getObject();
        }

        @Bean("twoSqlSessionTemplate")
        public SqlSessionTemplate getSqlSessionTemplate() throws Exception {
            SqlSessionTemplate template = new SqlSessionTemplate(getSqlSessionFactory());
            return template;
        }
    }
}
