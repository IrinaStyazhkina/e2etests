package ru.ozon.tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.ozon.pages.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

@Feature("Authorization")
@Story("Negative checks")
class LoginTest extends BaseTest {

    @AllureId("1717")
    @Test
    @DisplayName("Should not login if email input is empty")
    void shouldNotLoginWithEmptyEmail() {
        open(baseUrl, MainPage.class)
                .clickLoginButton()
                .tryToLoginWithEmail("")
                .checkError("Заполните почту");
    }

    @AllureId("1716")
    @Test
    @DisplayName("Should not login if email format is incorrect")
    void shouldNotLoginWithIncorrectEmail() {
        open(baseUrl, MainPage.class)
                .clickLoginButton()
                .tryToLoginWithEmail("not_email@email")
                .checkError("Некорректный формат почты");
    }

    @AllureId("1720")
    @Test
    @DisplayName("Should not login if user doesn't exist")
    void shouldNotLoginIfUserDoesNotExist() {
        open(baseUrl, MainPage.class)
                .clickLoginButton()
                .tryToLoginWithEmail("user_not_exists@test.net")
                .checkError("Пользователь с указанным email не найден");
    }

    @AllureId("1718")
    @Test
    @DisplayName("Should not login if phone input is empty")
    void shouldNotLoginWithEmptyPhone() {
        open(baseUrl, MainPage.class)
                .clickLoginButton()
                .tryToLoginWithPhone("")
                .checkError("Заполните телефон");
    }

    @AllureId("1719")
    @Test
    @DisplayName("Should not login if phone format is incorrect")
    void shouldNotLoginWithIncorrectPhoneFormat() {
        open(baseUrl, MainPage.class)
                .clickLoginButton()
                .tryToLoginWithPhone("0000000000000000000")
                .checkError("Некорректный формат телефона");
    }
}
