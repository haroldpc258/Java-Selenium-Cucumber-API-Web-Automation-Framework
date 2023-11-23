package com.globant.tests;

import com.globant.pages.HomePage;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected WebDriver driver;

    protected void setUpDriver(String browser) {
        switch (browser) {
            case "Chrome" -> {
                driver = new ChromeDriver();
            }
            case "Firefox" -> {
                driver = new FirefoxDriver();
            }
            case "Edge" -> {
                driver = new EdgeDriver();
            }
        }
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected void maximizeWindow() {
        driver.manage().window().maximize();
    }

    protected void shutDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public HomePage loadFirstPage() {
        return new HomePage(driver);
    }

    protected String getARandomFilm() {
        Response response = given()
                .when()
                .get("/films/")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, String>> films =  response.jsonPath().getList("results");
        return films.get((int) (Math.random() * films.size())).get("title");
    }

    protected String getCharacterName(int characterPath) {

        Response response = given()
                .when()
                .get("/people/" + characterPath)
                .then()
                .statusCode(200)
                .extract().response();

        return response.jsonPath().getString("name");
    }
}
