package com.au.petstore.utils;

import org.testng.annotations.BeforeSuite;

import java.util.Properties;
import java.util.Random;

public class TestDataLoader {
    private final Properties properties;
    public static TestDataLoader testDataLoader;


    private TestDataLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/testData.properties");
    }

    public static TestDataLoader getInstance(){
        if(testDataLoader==null){
            testDataLoader=new TestDataLoader();
        }
        return testDataLoader;
    }

    public String getPetStoreReourcePath(){
        String prop = properties.getProperty("petStoreResourcePath");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property pet Store resource path have issue");
    }

    public String petStoreResourceQueryPath(){
        String prop = properties.getProperty("petStoreResourceQueryPath");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property pet Store resource path have issue");
    }

    public String getPetStoreFindByStatusReourcePath(){
        String prop = properties.getProperty("petStoreResourceByFindByStatusPath");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property pet Store resource path have issue");
    }



    public String getPhotoUrl(){
        String prop = properties.getProperty("photoUrl");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property photoUrl have issue");
    }

    public int getPetID(){
        int prop = Integer.parseInt(properties.getProperty("petID"));
        if(prop>=0)
            return prop;
        else throw  new RuntimeException("property petID have issue");
    }

    public String getPetName(){
        String prop = properties.getProperty("name");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property name have issue");
    }

    public String getTagName(){
        String prop = properties.getProperty("tagName");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property tagName have issue");
    }

    public int getTagID(){
        int prop = Integer.parseInt(properties.getProperty("tagID"));
        if(prop>=0)
            return prop;
        else throw  new RuntimeException("property tagID have issue");
    }

    public int getCategoryID(){
        int prop = Integer.parseInt(properties.getProperty("categoryID"));
        if(prop>=0)
            return prop;
        else throw  new RuntimeException("property categoryID have issue");
    }

    public String getCategoryname(){
        String prop = properties.getProperty("categoryName");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property categoryName have issue");
    }

    public String getStatusAvailable(){
        String prop = properties.getProperty("statusAvailable");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property statusAvailable have issue");
    }

    public String getStatusSold(){
        String prop = properties.getProperty("statusSold");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property statusSold have issue");
    }
    public String getStatusPending(){
        String prop = properties.getProperty("statusPending");
        if(prop!=null)
            return prop;
        else throw  new RuntimeException("property statusPending have issue");
    }


}
