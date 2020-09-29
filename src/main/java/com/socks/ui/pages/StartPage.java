package com.socks.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class StartPage {
    public static StartPage open() throws InterruptedException {
        Selenide.open("/");
        Thread.sleep(15000);
        return new StartPage();
    }

    @Step
    public void loginAs(String username, String password){
        $("#login a").click();
        $("#username-modal").sendKeys(username);
        $("#password-modal").sendKeys(password);
        $("#login-modal p button").click();
    }

    @Step
    public void registerAs(String username, String firstname, String lastname, String email, String password){
        $("#register > a").click();
        $("#register-username-modal").sendKeys(username);
        $("#register-first-modal").sendKeys(firstname);
        $("#register-last-modal").sendKeys(lastname);
        $("#register-email-modal").sendKeys(email);
        $("#register-password-modal").sendKeys(password);
        $x("//*[@id='register-modal']//form//button").click();
    }

    @Step
    public SelenideElement invalidLoginCredentialsMessage() {
        return $("#login-message > div");
    }
}
