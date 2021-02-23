package ru.ozon.elements;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPopupIframe {

    private SelenideElement self = $("#authFrame"),
            phoneInput = $("[name='phone']"),
            emailInput = $("[name='email']"),
            submitButton = $("[type='submit']"),
            loginByEmailButton = $(byText("Войти по почте")),
            errorMessage = $("[qa-id='errorMessage']");

    @Step("Try to login with email: {email}")
    public LoginPopupIframe tryToLoginWithEmail(String email) {
        loginByEmailButton.click();
        emailInput.setValue(email);
        submitButton.click();
        return this;
    }

    @Step("Try to login with phone: {phone}")
    public LoginPopupIframe tryToLoginWithPhone(String phone) {
        phoneInput.setValue(phone);
        submitButton.click();
        return this;
    }

    @Step("Check error message displayed: {error}")
    public LoginPopupIframe checkError(String error) {
        errorMessage.shouldHave(text(error));
        return this;
    }

}
