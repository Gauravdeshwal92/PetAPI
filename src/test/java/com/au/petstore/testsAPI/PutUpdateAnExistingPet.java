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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class PutUpdateAnExistingPet extends TestUtils {

    Random random = new Random();
    int petID;
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


    @Test(dataProvider = "status")
    public void updatePetWithDifferentStatusForSamePetID(String status) {

        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response response = PetStoreAPI.put(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updatePetWithMandatoryFields() {

        try {
            Pet requestPet = petReqBodyBuilderWithMandatoryFields("Hunter");
            Response response = PetStoreAPI.put(requestPet);
            assertThat(response.statusCode(), is(equalTo(200)));
            Pet responsePet = response.as(Pet.class);
            assertPetSEquals(requestPet, responsePet);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


    @Test()
    public void updatePetWithDeletedPetID() {
        try {
            Pet requestPet = petReqBodyBuilder(status, petID);
            Response deletePetID = PetStoreAPI.delete(String.valueOf(petID));
            Response response = PetStoreAPI.post(requestPet);
            assertThat(response.statusCode(), is(equalTo(404)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void updatePetWithoutMandatoryFieldsError400() {
        try {
            String status = "pending";
            Pet requestPet = petReqBodyBuilderWithoutMandatoryFields(status, petID);
            Response response = PetStoreAPI.put(requestPet);
            assertThat(response.statusCode(), is(equalTo(400)));
            assertThat(response.path("message"), is(equalTo("bad input")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToUpdatePetBadRequestError400() {

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
    public void notAbleToCreatePetAsEmptyObjectError400() {

        try {
            Pet requestPet = new Pet();
            Response response = PetStoreAPI.put(requestPet);
            assertThat(response.statusCode(), is(equalTo(400)));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetWithoutBodyError405() {

        try {
            Response response = PetStoreAPI.putWithoutBody();
            assertThat(response.statusCode(), is(equalTo(405)));
            assertThat(response.path("message"), is(equalTo("no data")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void notAbleToCreatePetAsIDIsMissingError500() {

        try {
            Response response = given(getRequestSpec()).body("\"\"").when().put("pet/").then().spec(getResponseSpec()).extract().response();

            assertThat(response.statusCode(), is(equalTo(500)));
            assertThat(response.path("message"), is(equalTo("something bad happened")));
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }


}