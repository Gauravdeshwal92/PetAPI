package com.au.petstore.testsAPI;

import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GetPetByStatus extends TestUtils {
    Random random = new Random();
    int petID;

    @DataProvider(name = "status")
    public Object[][] petStoreStatus() {
        return new Object[][]{{"available"}, {"sold"}, {"Pending"}};
    }

    @BeforeTest
    public void generateRandomID() {
        int min = 1000;
        int max = 1000000;
        petID = random.nextInt((max - min) + 1) + min;
    }

    @Test(dataProvider = "status")
    public void getPetByStatus(String status) {

        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Response getPetStatusResponse = PetStoreAPI.getByQuery(status);
            assertThat(getPetStatusResponse.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPetByInvalidStatus() {

        try {

           String invalidStatus = "1234";
            Response getPetStatusResponse = PetStoreAPI.getByQuery(invalidStatus);
            assertThat(getPetStatusResponse.statusCode(), is(equalTo(400)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPetByRemovingStatus() {

        try {
            String invalidStatus = "";
            Response getPetStatusResponse = PetStoreAPI.getByQuery(invalidStatus);
            assertThat(getPetStatusResponse.statusCode(), is(equalTo(405|400)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
