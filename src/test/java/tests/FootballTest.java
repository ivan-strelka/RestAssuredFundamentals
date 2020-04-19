package tests;

import endpoints.EndPointsFootballAPI;
import config.FootballApiConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FootballTest extends FootballApiConfig {

    @Test
    public void getCompetitionsByMatchesDay() {
        given()
                .spec(football_requestSpec)
                .queryParam("matchday", 1)
                .when().get(EndPointsFootballAPI.GET_COMPETITION_MATCHES)
                .then().spec(football_responseSpec);
    }

    @Test
    public void getTeamCount() {
        given()
                .spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().body("count", equalTo(20))
                .spec(football_responseSpec);

    }

    @Test
    public void getCompetitionId() {
        given().log().all()
                .spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().body("competition.id", equalTo(2018))
                .spec(football_responseSpec);

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
                .then().spec(football_responseSpec)
                .extract().response();

        String jsonResponsAsString = response.asString();
        System.out.println(jsonResponsAsString);
    }

    @Test
    public void extractHeaders() {
        Response response = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().spec(football_responseSpec)
                .extract().response();

        Headers headers = response.getHeaders();
        String contentType = response.getHeader("Content-Type");
        Map<String, String> cookies = response.getCookies();
        System.out.println("contentTyp IS = " + contentType);
        System.out.println("Cookies IS = " + cookies);
    }

    @Test
    public void extractData() {
        String someData = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .jsonPath().getString("season.id");
        System.out.println("season.id IS = " + someData);

    }

    @Test
    public void extractAllData() {
        Response response = given().spec(football_requestSpec)
                .when().get(EndPointsFootballAPI.GET_COMPETITIONS_2018_TEAMS)
                .then().spec(football_responseSpec)
                .extract().response();

        List<String> allName = response.path("teams.name");
        for (String names : allName) {
            System.out.println("NAMES IS = " + names);
        }
    }


}
