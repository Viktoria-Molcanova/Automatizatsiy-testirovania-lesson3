package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.max.lesson3.seminar.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.seminar.accuweather.weather.Weather;
import org.max.lesson3.seminar.spoonacular.ConvertAmountsDto;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic("Прогноз погоды")
@Feature("Получение погоды на 15 дней")
public class GetWeatherOneDayTest extends AccuweatherAbstractTest {
    @Description("Получение погоды на 15 дней")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение погоды")
    @Owner("Виктория М.")
    @Link(name = "Документация API", url = "https://docs.accuweather.com/")
    @Issue("BUG-1234")

    @Test
    void getWeatherOneDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/294021")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(1, response.getDailyForecasts().size());
    }
    @Description("Получение погоды на 15 дней")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение погоды")
    @Owner("Виктория М.")
    @Link(name = "Документация API", url = "https://docs.accuweather.com/")
    @Issue("BUG-1234")

    @Test
    void getWeatherOneDay_missingApiKey_shouldReturn401() {

        given()
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/1day/294021")
                .then()
                .statusCode(401);
    }
    @Description("Получение погоды на 15 дней")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение погоды")
    @Owner("Виктория М.")
    @Link(name = "Документация API", url = "https://docs.accuweather.com/")
    @Issue("BUG-1234")

    @Test
    void getWeatherOneDay_invalidEndpoint_shouldReturn400() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/invalidEndpoint")
                .then()
                .statusCode(400);
    }
}