package com.au.petstore.testsAPI;

import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetPetByID extends TestUtils {

    Random random = new Random();
    int petID;
    String status = "available";
    Pet requestPet;

    @BeforeClass
    public void generateRandomID() {
        int min = 1000;
        int max = 1000000;
        petID = random.nextInt((max - min) + 1) + min;
        requestPet = petReqBodyBuilder(status, petID);
        Response response = PetStoreAPI.post(requestPet);
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test()
    public void getPetByID() {

        try {

            Response response = PetStoreAPI.get(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPetByInvalidID() {

        try {
            String invalidpetID = "";
            Response response = PetStoreAPI.get(String.valueOf(invalidpetID));
            assertThat(response.statusCode(), is(equalTo(405)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods ="getPetByID" )
    public void getDeletePetAsNotFound() {

        try {

            Response deleteResponse = PetStoreAPI.delete(String.valueOf(petID));
            assertThat(deleteResponse.statusCode(), is(equalTo(200)));
            Response response = PetStoreAPI.get(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(404)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
