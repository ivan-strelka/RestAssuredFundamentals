
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGamesTest extends TestConfig {

    @Test
    public void firstTest() {
        given()
                .log().all().spec(videoGames_requestSpec)
                .when().get("videogames/1")
                .then().log().all().spec(videoGames_responseSpec);

    }


}
