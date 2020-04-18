
import config.EndPointsFootballAPI;
import config.TestConfig;
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

}
