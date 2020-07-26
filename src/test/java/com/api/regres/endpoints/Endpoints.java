package com.api.regres.endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Endpoints {
    private static final String APPLICATION_JSON = "application/json";
    private static final String regresBaseURL="https://reqres.in";

//    use System.getProperty to pass url as parameter("env") in commandline
//    private static final String regresBaseURL= System.getProperty("sys.com.api.regres.baseURL");


    public static Response getUsers(RequestSpecification requestSpec){
        System.out.println("URL: GET "+regresBaseURL+"/api/users");
        return given().contentType(APPLICATION_JSON).when().spec(requestSpec).get(regresBaseURL+"/api/users");
    }

    public static Response deleteUsers(String json){
        System.out.println("URL: DELETE "+regresBaseURL+"/api/users/2");
        return given().contentType(APPLICATION_JSON).when().body(json).delete(regresBaseURL+"/api/users/2");
    }

    public static Response putUsers(String json){
        System.out.println("URL: PUT "+regresBaseURL+"/api/users/2");
        return given().contentType(APPLICATION_JSON).when().body(json).put(regresBaseURL+"/api/users/2");
    }

    public static Response postUsers(String json){
        System.out.println("URL: POST "+regresBaseURL+"/api/users");
        return given().contentType(APPLICATION_JSON).when().body(json).post(regresBaseURL+"/api/users");
    }
}
