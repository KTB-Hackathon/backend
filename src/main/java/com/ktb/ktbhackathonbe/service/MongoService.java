package com.ktb.ktbhackathonbe.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ktb.ktbhackathonbe.model.Image;
import com.ktb.ktbhackathonbe.repository.image.ImageRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MongoService {
    @Autowired
    ImageRepository imageRepository;

    Gson gson = new Gson();

    public String mongoTest(){

        Image image = imageRepository.findbyName("covy");

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", image.getId().toString());
        jsonObject.addProperty("name", image.getName());

        return gson.toJson(jsonObject);
    }
}
