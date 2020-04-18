package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/app/";

        RequestSpecification requestSpecificationOne = new RequestSpecBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();

        RestAssured.requestSpecification = requestSpecificationOne;

        ResponseSpecification responseSpecificationOne = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectHeader("Content-Type", "application/json")
                .expectHeader("Content-Length", "119")
                .build();

        RestAssured.responseSpecification = responseSpecificationOne;
    }


}
