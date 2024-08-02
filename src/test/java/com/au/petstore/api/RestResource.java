package com.au.petstore.api;

import com.au.petstore.pojo.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.StringReader;

import static com.au.petstore.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, Pet requestPet){

        return given(getRequestSpec())
                .body(requestPet)
                .when().post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response post(String path){

        return given(getRequestSpec())
                .when().post(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response postByQuery(String path,String petID){

        return  given(getRequestSpecWithoutJSONCONTENT()).pathParams("petID",petID).
                when().post(path).
                then().spec(getResponseSpec()).extract().response();

    }

    public static Response postFormData(String path,String petID,String name, String status){

        return  given(getRequestSpecWithFormData()).pathParams("petID",petID).
                formParam("name", name).
                formParam("status",status).
                when().post(path).
                then().spec(getResponseSpec()).extract().response();

    }


    public static Response postUploadImage(String path,String petID){

        return  given(getRequestSpecWithoutJSONCONTENT()).pathParams("petID",petID).
                multiPart("file", new File("src/test/resources/dog.jpeg")).
                when().post(path+"{petID}"+"/uploadImage").
                then().spec(getResponseSpec()).extract().response();

    }

    public static Response get(String path,String petID){

        return  given(getRequestSpec()).pathParams("petID",petID)
                .when().get(path+"{petID}").
                then().spec(getResponseSpec()).extract().response();

    }

    public static Response getByQuery(String path,String queryParameter){

        return  given(getRequestSpec()).param("status",queryParameter)
                .when().get(path).
                then().spec(getResponseSpec()).extract().response();

    }




    public static Response put(String path,Pet requestPet){

        return  given(getRequestSpec()).body(requestPet)
                .when().put(path).
                then().spec(getResponseSpec()).extract().response();

    }

    public static Response put(String path){

        return  given(getRequestSpec())
                .when().put(path).
                then().spec(getResponseSpec()).extract().response();

    }


    public static Response delete(String path, String petID){

        return  given(getRequestSpec()).pathParams("petID",petID)
                .when().delete(path+"{petID}").
                then().spec(getResponseSpec()).extract().response();

    }


}
