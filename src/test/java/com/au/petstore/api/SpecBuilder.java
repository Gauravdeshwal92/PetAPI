package com.au.petstore.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
   static String baseURI = "https://petstore.swagger.io/v2/";

    public static RequestSpecification getRequestSpec(){

         return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").
                 addHeader("api_key","special-key").
                setContentType(ContentType.JSON).log(LogDetail.ALL).build();

    }

    public static RequestSpecification getRequestSpecWithoutJSONCONTENT(){

        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").
                addHeader("api_key","special-key").log(LogDetail.ALL).build();

    }

    public static RequestSpecification getRequestSpecWithFormData(){

        return new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").
                addHeader("api_key","special-key").
                setContentType("application/x-www-form-urlencoded").log(LogDetail.ALL).build();

    }
    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).build();

    }
}
