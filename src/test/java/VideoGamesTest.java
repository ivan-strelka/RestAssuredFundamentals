
import config.EndPoints;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGamesTest extends TestConfig {

    @Test
    public void getGamesByIdTest() {
        given()
                .log().ifValidationFails().spec(videoGames_requestSpec)
                .when().get("videogames/1")
                .then().log().ifValidationFails().spec(videoGames_responseSpec);

    }

    @Test
    public void getAllGames() {
        given()
                .log().ifValidationFails().spec(videoGames_requestSpec)
                .when().get(EndPoints.GET_LIST_OF_ALL_VIDEO_GAMES)
                .then().log().ifValidationFails().spec(videoGames_responseSpec);
    }
}
