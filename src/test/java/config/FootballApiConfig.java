package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

import static org.hamcrest.Matchers.*;

public class FootballApiConfig {

    public static RequestSpecification football_requestSpec;
    public static ResponseSpecification football_responseSpec;

    @BeforeClass
    public static void setUp() {

        football_requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://api.football-data.org")
                .log(LogDetail.ALL)
                .setBasePath("/v2/")
                .setAccept(ContentType.JSON)
                .addHeader("X-Auth-Token", "01f36f37f4fd4f82bc93235a954310be")
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
                .expectHeader("Content-Encoding", "gzip")
                .expectResponseTime(lessThan(3000L))
                .build();

        RestAssured.requestSpecification = football_requestSpec;
        RestAssured.responseSpecification = football_responseSpec;


    }


}
