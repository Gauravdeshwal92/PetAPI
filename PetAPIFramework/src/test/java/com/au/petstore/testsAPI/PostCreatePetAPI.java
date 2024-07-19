package com.au.petstore.testsAPI;


import com.au.petstore.applicationApi.PetStoreAPI;
import com.au.petstore.pojo.Pet;
import com.au.petstore.utils.TestUtils;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.Random;

import static com.au.petstore.api.SpecBuilder.getRequestSpec;
import static com.au.petstore.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PostCreatePetAPI extends TestUtils {

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

    @AfterTest
    public void deleteRandomPetID() {
        try {
            Response response = PetStoreAPI.delete(String.valueOf(petID));
            assertThat(response.statusCode(), is(equalTo(200)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    @Test(dataProvider = "status")
    public void postAPIToCreatePetWithDifferentStatus(String status) {

        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void createPetWithMandatoryFields() {
        try {
            String status = "available";
            Pet requestPet = petReqBodyBuilderWithMandatoryFields(status);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void createPetWIthSameID() {

        try {
            Pet requestPet = petReqBodyBuilder("avaliable", petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet requestPet2 = petReqBodyBuilder("avaliable", petID);
            Response response2 = PetStoreAPI.post(requestPet2);
            assertThat(response2.statusCode(), is(equalTo(409)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void postAPIToCreatePetWithoutMandatoryFieldsError400() {
        try {
            String status = "pending";
            Pet requestPet = petReqBodyBuilderWithoutMandatoryFields(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(400)));
            assertThat(response.path("message"), is(equalTo("bad input")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetBadRequestError400() {

        try {
            String status = "123";
            Pet requestPet = petReqBodyBuilderWithoutMandatoryFields(status, petID);
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(400)));
            assertThat(response.path("message"), is(equalTo("bad input")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetAsResourceIsMissingError404() {

        try {
            String status = "pending";
            Pet requestPet = petReqBodyBuilderWithoutMandatoryFields(status, petID);
            Response response = PetStoreAPI.postWithoutResource(requestPet);
            assertThat(response.statusCode(), is(equalTo(404)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetAsEmptyObjectError400() {

        try {
            Pet requestPet = new Pet();
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(400)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetWithoutBodyError405() {

        try {
            Response response = PetStoreAPI.postWithoutBody();
            assertThat(response.statusCode(), is(equalTo(405)));
            assertThat(response.path("message"), is(equalTo("no data")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetAsIDIsMissingError500() {

        try {
            Response response = given(getRequestSpec()).body("\"\"").when().post("pet/").then().spec(getResponseSpec()).extract().response();

            assertThat(response.statusCode(), is(equalTo(500)));
            assertThat(response.path("message"), is(equalTo("something bad happened")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


}