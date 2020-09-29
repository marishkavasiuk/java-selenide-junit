package com.socks.ui.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverProvider;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.socks.ui.ModelHelper;
import com.socks.ui.model.UserPayload;
import com.socks.ui.model.UsersListResponse;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseUITest {

    @BeforeClass
    public static void setUp() {
        String runType = System.getProperty("runType"); // use to run on Jenkins Machine
        if (runType == null) {
            runType = System.getenv("runType"); // use to run local PC
            if (runType == null) {
                runType = "local";
            }
        }
        switch (runType) {
            case ("local"):
                Configuration.browser = "chrome";
                Configuration.baseUrl = "http://192.168.99.100";
                break;
            case ("docker"):
                Configuration.driverManagerEnabled = false;
                Configuration.browser = SelenoidWebDriverProvider.class.getName();
                Configuration.baseUrl = "http://192.168.56.11";
                break;
        }
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    protected  <T> T at(Class<T> pageClass){
        return Selenide.page(pageClass);
    }

    @Step
    public static UserPayload createNewUser(){
        ModelHelper modelHelper = new ModelHelper();
        UserPayload userPayload = modelHelper.getUserWithValidCredentialsMandatoryFields();
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userPayload)
                .post(Configuration.baseUrl+ "/register");
        return userPayload;
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }

    @AfterClass
    public static void deleteUsers() throws IOException {
        UsersListResponse users = asPojo(RestAssured.given().contentType(ContentType.JSON)
                .get(Configuration.baseUrl+ "/customers"), UsersListResponse.class);

        for(int i = 0; i < users.getEmbedded().getCustomer().size(); i ++){
            String userId = users.getEmbedded().getCustomer().get(i).getId();
            RestAssured.given().contentType(ContentType.JSON)
                    .delete(String.format(Configuration.baseUrl+ "/customers/%s", userId))
                    .then().log().all();
        }
    }

    @Step("Deserialize response into Java objects")
    public static <T> T asPojo(Response response, Class<T> tClass) throws IOException {
        InputStream inputStream = response.getBody().asInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, tClass);
    }

    public static class SelenoidWebDriverProvider implements WebDriverProvider {
        @Nonnull
        @Override
        public WebDriver createDriver(@Nonnull DesiredCapabilities capabilities) {
            DesiredCapabilities browser = new DesiredCapabilities();
            browser.setBrowserName("firefox");
            browser.setVersion("80.0");
            browser.setCapability("enableVNC", true);

            try {
                return new RemoteWebDriver(
                        URI.create("http://192.168.56.11:4444/wd/hub").toURL(), browser
                );
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

