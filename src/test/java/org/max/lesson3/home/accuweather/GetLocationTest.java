package org.max.lesson3.home.accuweather;

import org.apache.log4j.BasicConfigurator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetLocationTest extends AccuweatherAbstractTest {

    @Test
    void getLocation_autocomplete_returnKrasnoyarsk() {

        List<Location> response = given()
                .queryParam("apikey", getApiKey())
                .queryParam("q", "Krasnoyarsk")
                .when()
                .get(getBaseUrl()+"/locations/v1/cities/autocomplete")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Location.class);

        Assertions.assertEquals(10,response.size());
        Assertions.assertEquals("Krasnoyarsk", response.get(0).getLocalizedName());
    }

}
