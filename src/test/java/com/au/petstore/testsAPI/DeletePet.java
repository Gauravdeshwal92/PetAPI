package com.au.petstore.testsAPI;

import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.pojo.Success;
import com.au.petstore.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DeletePet extends TestUtils {


    Random random = new Random();
    int petID;
    String status = "available";


    @BeforeTest
    public void generateRandomID() {
        int min = 1000;
        int max = 1000000;
        petID = random.nextInt((max - min) + 1) + min;
    }


    @Test
    public void deletePetFromTheStoreWithValidPetID() {
        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Response deleteResponse = PetStoreAPI.delete(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(200)));
            Success deletePet = deleteResponse.as(Success.class);
            assertSuccess(String.valueOf(petID), deletePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNonExistencePetIDFromTheStore() {
        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Response deleteResponse = PetStoreAPI.delete(String.valueOf(petID));
            Response deleteAgainResponse = PetStoreAPI.delete(String.valueOf(petID));
            assertThat(deleteAgainResponse.statusCode(), is(equalTo(404)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletePetIDFromTheStoreWithoutMandatoryFieldPetID() {
        try {
            String invalidpetID = "";
            Response deleteAgainResponse = PetStoreAPI.delete(invalidpetID);
            assertThat(deleteAgainResponse.statusCode(), is(equalTo(405)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


}
