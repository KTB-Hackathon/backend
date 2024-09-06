package com.ktb.ktbhackathonbe.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "image")
@Data
public class Image {
    @MongoId
    private ObjectId id;
    private String name;
}
