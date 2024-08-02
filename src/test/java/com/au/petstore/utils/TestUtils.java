package com.au.petstore.utils;

import com.au.petstore.pojo.Category;
import com.au.petstore.pojo.Pet;
import com.au.petstore.pojo.Success;
import com.au.petstore.pojo.Tag;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestUtils {

    @BeforeSuite
    public static void cleanAllureResultsFolder() {
        File directory = new File("allure-results");
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                if (!file.isDirectory()) {
                    file.delete();
                }
            }
        }
    }

    public Pet petReqBodyBuilder(String status, Integer petID) {

        return Pet.builder().
                id(petID).
                category(categoryBuilder()).
                name(TestDataLoader.getInstance().getPetName()).
                photoUrls(urls()).
                tags(tagBuilder()).
                status(status).build();

    }

    public Pet petReqBodyBuilderWithoutMandatoryFields(String status, Integer petID) {

        return Pet.builder().
                id(petID).
                category(categoryBuilder()).
                tags(tagBuilder()).
                status(status).build();

    }

    public Pet petReqBodyBuilderWithMandatoryFields(String name) {

        return Pet.builder().
                name(name).
                photoUrls(urls()).build();

    }


    public Category categoryBuilder(){
        return Category.builder().
                id(TestDataLoader.getInstance().getCategoryID()).
                name(TestDataLoader.getInstance().getCategoryname()).build();
    }

    public List<Tag> tagBuilder(){
        return Arrays.asList(Tag.builder().
                id(TestDataLoader.getInstance().getTagID()).
                name(TestDataLoader.getInstance().getTagName()).build());
    }

    public List<String> urls(){
        return  Arrays.asList(TestDataLoader.getInstance().getPhotoUrl());
    }

    public void assertPetSEquals(Pet requestPet,Pet responsePet){
        assertThat(responsePet.getId(), is(equalTo(requestPet.getId())));
        assertThat(responsePet.getName(), is(equalTo(requestPet.getName())));
        assertThat(responsePet.getCategory().getId(), is(equalTo(requestPet.getCategory().getId())));
        assertThat(responsePet.getCategory().getName(), is(equalTo(requestPet.getCategory().getName())));
        assertThat(responsePet.getPhotoUrls(), is(equalTo(requestPet.getPhotoUrls())));
        assertThat(responsePet.getTags().get(0).getId(), is(equalTo(requestPet.getTags().get(0).getId())));
        assertThat(responsePet.getTags().get(0).getName(), is(equalTo(requestPet.getTags().get(0).getName())));
        assertThat(responsePet.getStatus(), is(equalToIgnoringCase(requestPet.getStatus())));
    }


    public void assertSuccess(String petID, Success delete){
        assertThat(delete.getCode(), is(equalTo(200)));
        assertThat(delete.getMessage(), is(equalTo(petID)));
    }

}

