package com.ktb.ktbhackathonbe;

import com.ktb.ktbhackathonbe.model.Image;
import com.ktb.ktbhackathonbe.repository.image.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.HashSet;
import java.util.Set;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
class KtbHackathonBeApplicationTests {

    @Autowired
    @Qualifier("ImageMongoTemplate")
    private MongoTemplate imageMongoTemplate;

    @Autowired
    ImageRepository imageRepository;

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

    @Test
    void repoTest(){
        assertEquals("66da7ca0c1109b6120964033",imageRepository.findByName("covy").getId().toString());
    }


}
