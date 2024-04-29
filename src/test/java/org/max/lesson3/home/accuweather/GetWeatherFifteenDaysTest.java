package org.max.lesson3.home.accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.weather.Weather;
import org.max.lesson3.seminar.accuweather.AccuweatherAbstractTest;
import io.qameta.allure.*;

import static io.restassured.RestAssured.given;


@Epic("Прогноз погоды")
@Feature("Получение погоды на 15 день")
public class GetWeatherFifteenDaysTest extends AccuweatherAbstractTest {

    @Description("Code 200")
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение погоды")
    @Owner("Виктория М.")
    @Link(name = "Документация API", url = "https://docs.accuweather.com/")
    @Issue("BUG-1234")

    @Test
    void GetLocationTest_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/290396")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(15,response.getDailyForecasts().size());
    }
    @DisplayName("Ошибка 401")
    @Description("GET ")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Виктория М.")
    @Story(value = "Тестирование метода getWeatherFifteenDays_missingApiKey_shouldReturn401")
    @Test
    void getWeatherFifteenDays_missingApiKey_shouldReturn401() {

        given()
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/15day/294021")
                .then()
                .statusCode(401);
    }
    @DisplayName("Ошибка 404")
    @Description("GET ")
    @Link("")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Виктория М.")
    @Story(value = "Тестирование метода getWeatherFifteenDays_NotFound_shouldReturn404")
    @Test

    void getWeatherFifteenDays_NotFound_shouldReturn404() {

        given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v12/daily/15day/invalidEndpoint")
                .then()
                .statusCode(404);
    }
}
