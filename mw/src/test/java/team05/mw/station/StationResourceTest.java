package team05.mw.station;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class StationResourceTest {

    @Test
    void list() {
        given()
                .when().get("/station/list")
                .then()
                .statusCode(200)
                .body(is("[{\"id\":\"RP-1\",\"name\":\"Heide Nord\",\"full\":false},{\"id\":\"RP-2\",\"name\":\"Zum Wilden Hirsch\",\"full\":true}]"));
    }

    @Test
    void getRp() {
        given()
                .when().get("/station/RP-1/status")
                .then()
                .statusCode(200)
                .body(is("{\"id\":\"RP-1\",\"name\":\"Heide Nord\",\"coordinates\":{\"lat\":0.0,\"lng\":0.0},\"capacity\":3,\"occupancy\":2}"));
    }
}
