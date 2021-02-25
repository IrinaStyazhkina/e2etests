package ru.ozon.pages.elements;

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
            errorMessage = $("[qa-id='errorMessage']"),
            alertPopup = $("div[data-widget='alertPopup']");

    @Step("Try to login with email: {email}")
    public LoginPopupIframe tryToLoginWithEmail(String email) {
        loginByEmailButton.click();
        emailInput.setValue(email);
        checkNoPopupVisible();
        submitButton.click();
        return this;
    }

    @Step("Try to login with phone: {phone}")
    public LoginPopupIframe tryToLoginWithPhone(String phone) {
        phoneInput.setValue(phone);
        checkNoPopupVisible();
        submitButton.click();
        return this;
    }

    @Step("Check error message displayed: {error}")
    public LoginPopupIframe checkError(String error) {
        errorMessage.shouldHave(text(error));
        return this;
    }

    private void checkNoPopupVisible() {
        if (alertPopup.has(text("Вы находитесь в зоне очень быстрой доставки!"))) {
            alertPopup.$("button").click();
        }
    }
}
