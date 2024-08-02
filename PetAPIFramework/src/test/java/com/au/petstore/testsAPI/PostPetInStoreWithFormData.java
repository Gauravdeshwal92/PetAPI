package com.au.petstore.testsAPI;

import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.pojo.Success;
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

public class PostPetInStoreWithFormData extends TestUtils {

    Random random = new Random();
    int petID=45670;
    String status = "available";
    Pet requestPet;

    @DataProvider(name = "nameAndStatus")
    public Object[][] petStoreStatus() {
        return new Object[][]{{"Jack","available"}, {"Jo","sold"}, {"Mike","Pending"}};
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


    @Test(dataProvider = "nameAndStatus")
    public void updateExistingPetWithNameAndStatus(String name, String status) {

        try {

            Response response = PetStoreAPI.postFormData(String.valueOf(petID), name, status);
            assertThat(response.statusCode(), is(equalTo(200)));
            Success success = response.as(Success.class);
            assertSuccess(String.valueOf(petID),success);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePetWithInvalidFields() {


        String name = "";
        String status ="";
        String invalidPetID ="";
            try {
            Response response = PetStoreAPI.postFormData(invalidPetID, name, status);
            assertThat(response.statusCode(), is(equalTo(200)));
            Success success = response.as(Success.class);
            assertSuccess(String.valueOf(petID),success);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
