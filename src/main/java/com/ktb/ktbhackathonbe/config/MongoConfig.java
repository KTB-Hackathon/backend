package com.ktb.ktbhackathonbe.config;

import com.ktb.ktbhackathonbe.repository.image.ImageRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = ImageRepository.class, mongoTemplateRef = "ImageMongoTemplate")
@EnableConfigurationProperties
public class MongoConfig {

    @Bean(name = "ImageMongoProperties")
    @ConfigurationProperties(prefix = "mongodb.image")
    @Primary
    public MongoProperties alphavantageProperties() {
        return new MongoProperties();
    }

    @Bean(name = "ImageMongoClient")
    public MongoClient mongoClient(@Qualifier("ImageMongoProperties") MongoProperties mongoProperties) {
        // MongoDB URI 생성
        String uri = "mongodb://" + mongoProperties.getHost() + ":" + mongoProperties.getPort();

        // MongoClientSettings를 사용하여 MongoClient 인스턴스 생성
        return MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .build());
    }

    @Primary
    @Bean(name = "ImageMongoDBFactory")
    public MongoDatabaseFactory mongoDatabaseFactory(
            @Qualifier("ImageMongoClient") MongoClient mongoClient,
            @Qualifier("ImageMongoProperties") MongoProperties mongoProperties) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, mongoProperties.getDatabase());
    }

    @Primary
    @Bean(name = "ImageMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("ImageMongoDBFactory") MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }
}
