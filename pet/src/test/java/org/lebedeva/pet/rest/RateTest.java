package org.lebedeva.pet.rest;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RateTest {

    @Test
    public void getWeather() {

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5")
                .then()
                .statusCode(200)
                .body("ccy[0]", equalTo("USD"))
                .body("ccy[1]", equalTo("EUR"))
                .body("ccy[2]", equalTo("RUR"))
                .log().all();
    }
}
