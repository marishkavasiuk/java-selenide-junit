package com.socks.ui.test;

import com.socks.ui.model.UserPayload;
import com.socks.ui.pages.ProductPage;
import com.socks.ui.pages.MainPage;
import com.socks.ui.pages.ShoppingCartPage;
import com.socks.ui.pages.StartPage;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;

public class TestAddProductToBucket extends BaseUITest {

    @Before
    public void loginWithValidCredentials() throws InterruptedException {
        UserPayload userPayload = createNewUser();
        StartPage.open().loginAs(userPayload.getUsername(), userPayload.getPassword());
    }

    @Test
    public void addProductTest() {
        String productName = "Colourful";
        at(MainPage.class).addProductToBucket(productName);
        at(ProductPage.class).colourfulTitle().shouldHave(text(productName));
        at(ProductPage.class).addToBucketBtn().click();
        at(ProductPage.class).bucketBtn().click();
        at(ShoppingCartPage.class).itemsInBucket().shouldHaveSize(1);
        at(ShoppingCartPage.class).itemNameInBucket(productName).should(enabled);
    }
}
