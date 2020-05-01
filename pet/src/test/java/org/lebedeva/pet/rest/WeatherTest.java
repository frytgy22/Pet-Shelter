package org.lebedeva.pet.rest;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class WeatherTest {

    @Test
    public void getWeather() {

        baseURI = "http://api.openweathermap.org/data/2.5/";

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .when()
                .get("/weather?q=Dnipro&appid=bc5422a1c3cfc20a7193c6d338279896")
                .then()
                .statusCode(200)
                .body("name", equalTo("Dnipro"))
                .log().all();

        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON).accept(ContentType.JSON)
                .get("/weather?q=London&appid=bc5422a1c3cfc20a7193c6d338279896")
                .then()
                .statusCode(200)
                .body("name", equalTo("London"))
                .log().all();
    }
}
