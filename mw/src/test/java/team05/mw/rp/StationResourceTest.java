package team05.mw.rp;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class StationResourceTest {

    @Test
    void list() {
        given()
                .when().get("/rp")
                .then()
                .statusCode(200)
                .body(is("[{\"id\":\"RP-1\",\"name\":\"Heide Nord\",\"full\":false},{\"id\":\"RP-2\",\"name\":\"Zum Wilden Hirsch\",\"full\":true}]"));
    }

    @Test
    void getRp() {
        given()
                .when().get("/rp/RP-1")
                .then()
                .statusCode(200)
                .body(is("{\"id\":\"RP-1\",\"name\":\"Heide Nord\",\"coordinates\":{\"lat\":0.0,\"lng\":0.0},\"capacity\":3,\"occupancy\":2}"));
    }
}
