
import config.EndPointsVideoGamesAPI;
import config.TestConfig;
import io.restassured.response.Response;
import model.VideoGame;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.lessThan;

public class VideoGamesTest extends TestConfig {

    @Test
    public void getGamesByIdTest() {
        given()
                .spec(videoGames_requestSpec)
                .when().get("videogames/1")
                .then().spec(videoGames_responseSpec);

    }

    @Test
    public void getAllGames() {
        given()
                .spec(videoGames_requestSpec)
                .when().get(EndPointsVideoGamesAPI.GET_LIST_OF_ALL_VIDEO_GAMES)
                .then().spec(videoGames_responseSpec);
    }

    @Test
    public void createNewGame11ByJson() {
        //bad practice
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"NewGame\",\n" +
                "  \"releaseDate\": \"2020-04-18T13:25:26.223Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given()
                .spec(videoGames_requestSpec)
                .body(gameBodyJson)
                .when().post(EndPointsVideoGamesAPI.ADD_A_NEW_VIDEO_GAME)
                .then().spec(videoGames_responseSpec);


    }

    @Test
    public void createNewGameByXml() {
        //bad practice
        String gameBodyXml = "  <videoGame category=\"Shooter\" rating=\"Universal\">\n" +
                "    <id>12</id>\n" +
                "    <name>Resident Evil 81</name>\n" +
                "    <releaseDate>2005-10-01T00:00:00+01:00</releaseDate>\n" +
                "    <reviewScore>99</reviewScore>\n" +
                "  </videoGame>";

        given().body(gameBodyXml).spec(videoGames_requestSpec)
                .header("Accept", "application/xml")
                .header("Content-Type", "application/xml")
                .when().post(EndPointsVideoGamesAPI.ADD_A_NEW_VIDEO_GAME)
                .then().spec(videoGames_responseSpec);


    }

    @Test
    public void updateGame11() {
        //bad practice
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"NewGameUpdate\",\n" +
                "  \"releaseDate\": \"2020-04-18T13:25:26.223Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Driving\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";
        given().body(gameBodyJson).spec(videoGames_requestSpec)
                .when().put(EndPointsVideoGamesAPI.UPDATE_A_VIDEO_GAME)
                .then().spec(videoGames_responseSpec);

    }

    @Test
    public void deleteGame11() {
        given().spec(videoGames_requestSpec)
                .when().delete(EndPointsVideoGamesAPI.DELETE_A_VIDEO_GAME)
                .then().spec(videoGames_responseSpec);
    }

    @Test
    public void getSingleGame() {
        given().spec(videoGames_requestSpec)
                .pathParam("videoGameId", 5)
                .when().get(EndPointsVideoGamesAPI.GET_A_SINGLE_VIDEO_GAME_BY_ID)
                .then().spec(videoGames_responseSpec);
    }

    @Test
    public void testVideoGameSerialisationByJson() {
        VideoGame videoGame = new VideoGame("14", "GameId14", "1998-12-12",
                "88", "trash", "PG-13");

        given().body(videoGame).spec(videoGames_requestSpec)
                .when().post(EndPointsVideoGamesAPI.ADD_A_NEW_VIDEO_GAME)
                .then().spec(videoGames_responseSpec);

    }

    @Test
    public void testJsonScema() {
        given().spec(videoGames_requestSpec)
                .pathParam("videoGameId", 5)
                .when().get(EndPointsVideoGamesAPI.GET_A_SINGLE_VIDEO_GAME_BY_ID)
                .then().body(matchesJsonSchemaInClasspath("VideoGameJsonScema.json"));
    }

    @Test
    public void convertJsonToPojo() {
        Response response =  given().spec(videoGames_requestSpec)
                .pathParam("videoGameId", 5)
                .when().get(EndPointsVideoGamesAPI.GET_A_SINGLE_VIDEO_GAME_BY_ID);

        VideoGame videoGame = response.getBody().as(VideoGame.class);
        System.out.println(videoGame.toString());

    }
}
