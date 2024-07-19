package com.au.petstore.applicationApi;

import com.au.petstore.api.RestResource;
import com.au.petstore.pojo.Pet;
import com.au.petstore.utils.TestDataLoader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.au.petstore.api.SpecBuilder.getRequestSpec;
import static com.au.petstore.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.*;

public class PetStoreAPI {

    public static Response post(Pet requestPet){

        return RestResource.post(TestDataLoader.getInstance().getPetStoreReourcePath(), requestPet);

    }

    public static Response postUploadImage(String petID){

        return RestResource.postUploadImage(TestDataLoader.getInstance().getPetStoreReourcePath(), petID);

    }

    public static Response postFormData(String petID, String name, String status){

        return RestResource.postFormData(TestDataLoader.getInstance().petStoreResourceQueryPath(), petID,name,status);

    }



    public static Response postByQuery(String petID){

        return RestResource.postByQuery(TestDataLoader.getInstance().petStoreResourceQueryPath(), petID);

    }

    public static Response postWithoutResource(Pet requestPet){

        return RestResource.post("/", requestPet);

    }

    public static Response postWithoutBody(){

        return RestResource.post(TestDataLoader.getInstance().getPetStoreReourcePath());

    }

    public static Response get(String petID){

        return  RestResource.get(TestDataLoader.getInstance().getPetStoreReourcePath(), petID);

    }

    public static Response getByQuery(String queryParameter){

        return  RestResource.getByQuery(TestDataLoader.getInstance().getPetStoreFindByStatusReourcePath(), queryParameter);

    }




    public static Response put(Pet requestPet){

        return RestResource.put(TestDataLoader.getInstance().getPetStoreReourcePath(),requestPet );

    }

    public static Response putWithoutBody(){

        return RestResource.put(TestDataLoader.getInstance().getPetStoreReourcePath());

    }


    public static Response delete(String petID){

        return  RestResource.delete(TestDataLoader.getInstance().getPetStoreReourcePath(), petID);

    }



}
