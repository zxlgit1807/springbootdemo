package com.zxl.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description: 多数据源配置
 */
@Component
public class MongoConfig {

    /**
     * 获取配置信息
     */
    @Data
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    @Component
    public class MultipleMongoProperties {
        private MongoProperties primary = new MongoProperties();
       // private MongoProperties second = new MongoProperties();
    }

    @Configuration
    @EnableMongoRepositories(basePackages = "com.zxl.dao.mongo.primary",
            mongoTemplateRef = primaryMongodb.MONGO_TEMPLATE)
    public static class primaryMongodb {

        private static final String MONGO_TEMPLATE = "primaryMongoTemplate";

        @Autowired
        private MultipleMongoProperties multipleMongoProperties;

        @Primary
        @Bean(name = MONGO_TEMPLATE)
        public MongoTemplate primaryMongoTemplate() throws Exception {
            return new MongoTemplate(primaryFactory());
        }

        @Bean("mongoPrimaryFactory")
        @Primary
        public MongoDbFactory primaryFactory() throws Exception {
            MongoProperties mongo = multipleMongoProperties.primary;
            ServerAddress seeds = new ServerAddress(mongo.getHost(), mongo.getPort());
            MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
            MongoClientOptions options = builder.build();
            MongoCredential credential = MongoCredential.createCredential(mongo.getUsername(), mongo.getDatabase(), mongo.getPassword());
            MongoClient mongoClient = new MongoClient(seeds, credential, options);
            return new SimpleMongoDbFactory(mongoClient, mongo.getDatabase());
        }
    }
}
