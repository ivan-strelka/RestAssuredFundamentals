
import config.EndPointsVideoGamesAPI;
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
                .when().get(EndPointsVideoGamesAPI.GET_LIST_OF_ALL_VIDEO_GAMES)
                .then().log().ifValidationFails().spec(videoGames_responseSpec);
    }

    @Test
    public void createNewGame11ByJson() {
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
                .when().post(EndPointsVideoGamesAPI.ADD_A_NEW_VIDEO_GAME)
                .then().log().ifValidationFails().spec(videoGames_responseSpec);


    }

    @Test
    public void createNewGameByXml() {
        String gameBodyXml = "  <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>12</id>\n" +
                "    <name>Resident Evil 81</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+01:00</releaseDate>\n" +
                "    <reviewScore>99</reviewScore>\n" +
                "  </videoGame>";

        given().body(gameBodyXml)
                .log().all()
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml")
                .when().post(EndPointsVideoGamesAPI.ADD_A_NEW_VIDEO_GAME)
                .then().log().all().spec(videoGames_responseSpec);


    }

    @Test
    public void updateGame11() {
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"NewGameUpdate\",\n" +
                "  \"releaseDate\": \"2020-04-18T13:25:26.223Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";
        given().body(gameBodyJson)
                .when().put(EndPointsVideoGamesAPI.UPDATE_A_VIDEO_GAME)
                .then().log().all().spec(videoGames_responseSpec);

    }

    @Test
    public void deleteGame11() {
        given()
                .when().delete(EndPointsVideoGamesAPI.DELETE_A_VIDEO_GAME)
                .then().log().all().spec(videoGames_responseSpec);
    }

    @Test
    public void getSingleGame() {
        given()
                .pathParam("videoGameId", 5)
                .when().get(EndPointsVideoGamesAPI.GET_A_SINGLE_VIDEO_GAME_BY_ID)
                .then().log().all().spec(videoGames_responseSpec);
    }


}
