package com.au.petstore.testsAPI;

import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PostUploadPetImage extends TestUtils {

    Random random = new Random();
    int petID=45670;
    String status = "available";
    Pet requestPet;

    @DataProvider(name = "status")
    public Object[][] petStoreStatus() {
        return new Object[][]{{"available"}, {"sold"}, {"Pending"}};
    }

    @BeforeClass
    public void generateRandomID() {
        int min = 1000;
        int max = 1000000;
        petID = random.nextInt((max - min) + 1) + min;
        petID = random.nextInt((max - min) + 1) + min;
        requestPet = petReqBodyBuilder(status, petID);
        Response response = PetStoreAPI.post(requestPet);
        assertThat(response.statusCode(), is(equalTo(200)));
    }


    @Test
    public void updateExistingPetWithImage() {

        try {

            Response response = PetStoreAPI.postUploadImage(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(200)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePetWithMandatoryFields() {

        try {
            Response response = PetStoreAPI.postByQuery(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(200)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePetWithoutMandatoryFields() {

        String petID = "";
        try {
            Response response = PetStoreAPI.postUploadImage(petID);
            assertThat(response.statusCode(), is(equalTo(415)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void updatePetWithDeletedPetID() {
        try {
            Response deletePetResponse = PetStoreAPI.delete(String.valueOf(petID));
            Response response = PetStoreAPI.postUploadImage(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(404)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }


}
