package services;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;

public class BaseService {
    protected static RequestSpecification defaultRequestSpecification() {
        RestAssured.baseURI = ConfigManager.getBaseURI();
        return given()
                .header("Content-type", "application/json");
    }

}
