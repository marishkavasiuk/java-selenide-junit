package com.socks.ui;

import com.github.javafaker.Faker;
import com.socks.ui.model.UserPayload;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

public class ModelHelper {
    public static Faker faker = new Faker();

    @Step
    public UserPayload getUserWithValidCredentials() {
        return new UserPayload(RandomStringUtils.randomAlphanumeric(6),
                faker.name() + "@email.com", faker.name().username(), faker.name().firstName(), faker.name().lastName());
    }

    @Step
    public UserPayload getUserWithValidCredentialsMandatoryFields() {
        return new UserPayload(RandomStringUtils.randomAlphanumeric(6),
                faker.name() + "@email.com", faker.name().username());
    }
    
}
