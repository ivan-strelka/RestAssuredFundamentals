
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {

    @Test
    public void firstTest() {
        given()
                .log()
                .all()
                .when().get("videogames/1")
                .then().log().all();

    }


}
