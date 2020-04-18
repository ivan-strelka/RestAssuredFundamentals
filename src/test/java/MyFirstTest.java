
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {

    @Test
    public void firstTest() {
        given().header("accept", "application/json").
                when().get("videogames/1").
                then().statusCode(200);

    }


}
