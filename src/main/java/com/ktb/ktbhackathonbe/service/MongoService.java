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
        System.out.println("MongoService start");

        ObjectId id = new ObjectId("66da7ca0c1109b6120964033");
        Optional<Image> result = imageRepository.findById(id);

        JsonObject jsonObject = new JsonObject();
        if (result.isPresent()) {  // Optional이 비어있지 않은지 확인
            jsonObject.addProperty("name", result.get().getName());
        } else {
            jsonObject.addProperty("error", "Image not found");
        }

        System.out.println("MongoService end");

        return gson.toJson(jsonObject);
    }
}
