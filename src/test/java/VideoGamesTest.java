
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

    @Test
    public void createNewGameByJson() {
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"NewGame\",\n" +
                "  \"releaseDate\": \"2020-04-18T13:25:26.223Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given()
                .log().ifValidationFails().spec(videoGames_requestSpec)
                .body(gameBodyJson)
                .when().post(EndPoints.ADD_A_NEW_VIDEO_GAME)
                .then().log().ifValidationFails().spec(videoGames_responseSpec);


    }
}
