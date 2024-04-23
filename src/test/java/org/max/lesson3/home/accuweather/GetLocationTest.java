package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import org.apache.log4j.BasicConfigurator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.location.Location;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("GetLocationTest")
@Feature("GetLocationTest")
public class GetLocationTest extends AccuweatherAbstractTest {
    @Description("Получение погоды на 15 дней")
    @Severity(SeverityLevel.NORMAL)
    @Story("GetLocationTest")
    @Owner("Виктория М.")
    @Link(name = "Документация API", url = "https://docs.accuweather.com/")
    @Issue("BUG-1234")

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
