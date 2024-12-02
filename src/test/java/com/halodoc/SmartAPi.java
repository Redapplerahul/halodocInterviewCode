package com.halodoc;

import TestCases.TestCaseSuite;
import Utilities.ConfigReader;
import Utilities.Utility;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;


public class SmartAPi extends Utility {

    ConfigReader configReader;


    public SmartAPi() throws IOException {
        configReader = new ConfigReader();
    }

    public static String id;
    public static String updatedname;
    public static String name;

    public void createObject() {

        JSONObject jsonObject;
        Logger logger = Logger.getLogger("SmartAPi.class");
        Response response=null;


        test=reports.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        try {

            String body = "{\n" +
                    "   \"name\": \"xxs IPHONE xx\",\n" +
                    "   \"data\": {\n" +
                    "      \"year\": 2023,\n" +
                    "      \"price\": 1849,\n" +
                    "      \"CPU model\": \"M1\",\n" +
                    "      \"Hard disk size\": \"1 TB\"\n" +
                    "   }\n" +
                    "}";
            RestAssured.baseURI = configReader.getcreateobject();

            RequestSpecification requestSpecification = RestAssured.given();

            requestSpecification.body(body);

            requestSpecification.contentType("application/json");

               response = requestSpecification.post();


            int statusCode = response.getStatusCode();

            logger.info(String.valueOf(statusCode));

            Assert.assertEquals(statusCode, 400);


            jsonObject = new JSONObject(response.getBody().asString());

            id = jsonObject.getString("id");

            updatedname = jsonObject.getString("name");

        } catch (AssertionError e)
        {
            e.printStackTrace();
            throw e;
        }

    }


    public void updateObject() {
        try {
            RestAssured.baseURI = configReader.getputobject();
            RestAssured.basePath = SmartAPi.id;
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.contentType("application/json");


            name = new Faker().name().firstName();
            byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/put.json"));
            String body = new String(bytes).replace("namewildcard", name);
            requestSpecification.body(body);
            Response response = requestSpecification.put();
            System.out.println(response.getBody().asString());

            JSONObject jsonObject = new JSONObject(response.asString());

            if (jsonObject.get("name").toString().equals(name)) {

                System.out.println("Name is Updated");
            } else {
                System.out.println("Name is not Updated");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void validateDetails() {
        try {

            RestAssured.baseURI = configReader.getputobject();
            RestAssured.basePath = SmartAPi.id;
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.contentType("application/json");

            Response response = requestSpecification.get();
            System.out.println(response.getBody().asString());

            JSONObject jsonObject = new JSONObject(response.asString());

            if (jsonObject.get("name").toString().equals(name)) {

                System.out.println("Details from POST and GaET APIS are Matching");
            } else {
                System.out.println("Details from POST and GET APIS are Not Matching");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
