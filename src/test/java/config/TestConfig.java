package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.*;

public class TestConfig {

    public static RequestSpecification videoGames_requestSpec;
    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification videoGames_responseSpec;
    public static ResponseSpecification football_responseSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setUp() {
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
                .build();

        football_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .log(LogDetail.ALL)
                .setBasePath("/v2/")
                .setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token","01f36f37f4fd4f82bc93235a954310be")
                .build();

        RestAssured.requestSpecification = videoGames_requestSpec;

        videoGames_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .expectContentType(ContentType.JSON)
                .expectHeader("Content-Type", "application/json")
                .expectResponseTime(lessThan(2000L))
                .build();

        football_responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .expectHeader("Content-Type", "application/json;charset=UTF-8")
                .expectHeader("X-Application-Context", "application:production")
                .expectHeader("X-API-Version", "v2")
                .expectHeader("Access-Control-Allow-Credentials", "true")
                .expectHeader("X-Authenticated-Client", "John Str")
                .expectHeader("Content-Language", "en")
                .expectHeader("Content-Encoding", "gzip")
                .expectResponseTime(lessThan(3000L))
                .build();


        RestAssured.responseSpecification = videoGames_responseSpec;

        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpec;




    }


}
