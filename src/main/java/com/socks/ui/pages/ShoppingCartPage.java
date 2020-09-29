package com.socks.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class ShoppingCartPage {
    @Step
    public ElementsCollection  itemsInBucket(){
        return $$("#cart-list > tr");
    }

    @Step
    public SelenideElement itemNameInBucket(String name){
        return $x(String.format("//a[text()='%s']", name));
    }
}
