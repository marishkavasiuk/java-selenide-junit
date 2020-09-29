package com.socks.ui.test;

import com.socks.ui.ModelHelper;
import com.socks.ui.model.UserPayload;
import com.socks.ui.pages.MainPage;
import com.socks.ui.pages.StartPage;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;

public class TestRegistration extends BaseUITest {
    @Test
    public void userCanBeRegisteredTest() throws InterruptedException {
        ModelHelper modelHelper = new ModelHelper();
        UserPayload userPayload = modelHelper.getUserWithValidCredentials();
        StartPage.open().registerAs(userPayload.getUsername(), userPayload.getFirstname(), userPayload.getLastname(),
                userPayload.getEmail(), userPayload.getPassword());
        at(MainPage.class).logoutBtn().shouldHave(text("Logout"));
        at(MainPage.class).loggedInAs().shouldHave(text
                (String.format("Logged in as %s %s", userPayload.getFirstname(), userPayload.getLastname())));
    }
}
