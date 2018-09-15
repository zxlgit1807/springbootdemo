package com.zxl.config;

import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;
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
 * @Description: 多数据源配置（主要就是数据源、factory及事物管理）。这里的写法是不用包路径下对应不同的数据源
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

        @Bean("oneDataSourceProperties")
        @Primary
        @ConfigurationProperties("spring.datasource.one")
        public DataSourceProperties getDataSourceProperties() {
            return new DataSourceProperties();
        }

        /**
         * 没有实现分布式事物写法
         * @return
         */
      /*  @Bean("oneDataSource")
        @Primary
        public DataSource getDataSource() {
            DruidDataSource dataSource = new DruidDataSource();
            DataSourceProperties dataSourceProperties = getDataSourceProperties();
            dataSource.setUrl(dataSourceProperties.getUrl().trim());
            dataSource.setUsername(dataSourceProperties.getUsername().trim());
            dataSource.setPassword(dataSourceProperties.getPassword().trim());
            return dataSource;
        }*/

        /**
         * 分布式事物写法
         * @return
         */
        @Bean("oneDataSource")
        @Primary
        public DataSource getDataSource() {
            DruidXADataSource xaDataSource = new DruidXADataSource();
            DataSourceProperties dataSourceProperties = getDataSourceProperties();
            xaDataSource.setUrl(dataSourceProperties.getUrl().trim());
            xaDataSource.setUsername(dataSourceProperties.getUsername().trim());
            xaDataSource.setPassword(dataSourceProperties.getPassword().trim());
            // 实现分布式事物关键，将dataSource交给Atomikos托管
            AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
            ds.setXaDataSource(xaDataSource);
            ds.setUniqueResourceName("jtaOneDataSource");
            return ds;
        }

        @Bean("oneMybatisProperties")
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
     *
     */
    @Configuration
    @MapperScan(basePackages = "com.zxl.dao.mysql.two", sqlSessionFactoryRef = "twoSqlSessionFactory",
            sqlSessionTemplateRef = "twoSqlSessionTemplate", annotationClass = Mapper.class)
    public static class TwoDataSourceConfig {


        @Bean("twoDataSourceProperties")
        @ConfigurationProperties("spring.datasource.two")
        public DataSourceProperties getDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean("twoDataSource")
        public DataSource getDataSource() {
            DruidXADataSource xaDataSource = new DruidXADataSource();
            DataSourceProperties dataSourceProperties = getDataSourceProperties();
            xaDataSource.setUrl(dataSourceProperties.getUrl().trim());
            xaDataSource.setUsername(dataSourceProperties.getUsername().trim());
            xaDataSource.setPassword(dataSourceProperties.getPassword().trim());

            AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
            ds.setXaDataSource(xaDataSource);
            ds.setUniqueResourceName("jtaTwoDataSource");
            return ds;
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
