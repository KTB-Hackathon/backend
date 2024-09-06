package com.ktb.ktbhackathonbe.repository.image;

import com.ktb.ktbhackathonbe.model.Image;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    Optional<Image> findById(ObjectId id);
}
