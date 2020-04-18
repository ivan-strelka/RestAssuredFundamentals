
import config.EndPointsFootballAPI;
import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FootballTest extends TestConfig {

    @Test
    public void getCompetitionsByMatchesDay() {
        given().log().all()
                .spec(football_requestSpec)
                .queryParam("matchday", 1)
                .when().get(EndPointsFootballAPI.GET_COMPETITION_MATCHES)
                .then().log().all().spec(football_responseSpec);
    }

    @Test
    public void getTeamCount() {
        given().log().all()
                .spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().body("count", equalTo(20))
                .log().all().spec(football_responseSpec);

    }

    @Test
    public void getCompetitionId() {
        given().log().all()
                .spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().body("competition.id", equalTo(2018))
                .log().all().spec(football_responseSpec);

    }

    @Test
    public void getTeamData() {
        String responsBody = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS).asString();
        System.out.println(responsBody);
    }

    @Test
    public void getTeamData2() {
        Response response = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().log().all().spec(football_responseSpec)
                .extract().response();

        String jsonResponsAsString = response.asString();
        System.out.println(jsonResponsAsString);
    }

    @Test
    public void extractHeaders() {
        Response response = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().log().all().spec(football_responseSpec)
                .extract().response();

        Headers headers = response.getHeaders();
        String contentType = response.getHeader("Content-Type");
        System.out.println(contentType);
    }
}
