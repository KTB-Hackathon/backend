package com.ktb.ktbhackathonbe.repository.image;

import com.ktb.ktbhackathonbe.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    List<Image> findByArea(String area);
}
