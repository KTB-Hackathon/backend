package com.ktb.ktbhackathonbe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Set;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
class KtbHackathonBeApplicationTests {

    @Autowired
    @Qualifier("ImageMongoTemplate")
    private MongoTemplate imageMongoTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void connectionTest(){
        assertNotNull(imageMongoTemplate);
        Set<String> collectionNames = imageMongoTemplate.getDb().listCollectionNames().into(new HashSet<>());
        int collectionCount = collectionNames.size();
        assertTrue(collectionCount == 1);
    }


}
