package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class VideoGameConfig {

    public static RequestSpecification videoGames_requestSpec;
    public static ResponseSpecification videoGames_responseSpec;

    @BeforeClass
    public static void setup() {

        videoGames_requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .log(LogDetail.ALL)
                .setPort(8080)
                .setBasePath("/app/")
                .setAccept(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept-Encoding:", "gzip, deflate, br")
                .addHeader("Accept-Language:", "en-US,en;q=0.9,ru;q=0.8")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        videoGames_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .expectContentType(ContentType.JSON)
                .expectHeader("Content-Type", "application/json")
                .expectResponseTime(lessThan(3000L))
                .build();


        RestAssured.requestSpecification = videoGames_requestSpec;
        RestAssured.responseSpecification = videoGames_responseSpec;

    }
}