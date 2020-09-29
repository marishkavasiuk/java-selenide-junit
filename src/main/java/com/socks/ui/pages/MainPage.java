package com.socks.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    @Step
    public SelenideElement logoutBtn() {
        return $("#logout > a");
    }
    @Step
    public SelenideElement loggedInAs() {
        return $("#howdy > a");
    }

    @Step
    public void addProductToBucket(String name) {
        $x(String.format("//img[contains(@src, '%s')]", name.toLowerCase())).click();
    }

}
