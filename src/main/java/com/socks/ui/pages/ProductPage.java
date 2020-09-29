package com.socks.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    @Step
    public SelenideElement colourfulTitle(){
        return $("#title");
    }
    @Step
    public SelenideElement addToBucketBtn(){
        return $("#buttonCart");
    }

    @Step
    public SelenideElement bucketBtn(){
        return $("#basket-overview > a");
    }
}
