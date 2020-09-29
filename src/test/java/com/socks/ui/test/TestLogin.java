package com.socks.ui.test;

import com.socks.ui.ModelHelper;
import com.socks.ui.model.UserPayload;
import com.socks.ui.pages.MainPage;
import com.socks.ui.pages.StartPage;
import org.junit.Test;

import static com.codeborne.selenide.Condition.text;

public class TestLogin extends BaseUITest{

    @Test
    public void userCanLoginWithValidCredentialsTest() throws InterruptedException {
        UserPayload userPayload = createNewUser();
        StartPage.open().loginAs(userPayload.getUsername(), userPayload.getPassword());
        at(MainPage.class).logoutBtn().shouldHave(text("Logout"));
    }

    @Test
    public void userCanntLoginWithInValidCredentialsTest() throws InterruptedException {
        ModelHelper modelHelper = new ModelHelper();
        UserPayload userPayload = modelHelper.getUserWithValidCredentials();
        StartPage.open().loginAs(userPayload.getUsername(), userPayload.getPassword());
        at(StartPage.class).invalidLoginCredentialsMessage().shouldHave(text("Invalid login credentials."));
    }

}
