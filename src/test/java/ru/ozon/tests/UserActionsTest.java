package ru.ozon.tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ozon.allure.Component;
import ru.ozon.pages.CartPage;
import ru.ozon.pages.FavouritesPage;
import ru.ozon.pages.MainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static ru.ozon.helpers.TestHelper.prepareCookies;

@Feature("User Actions")
public class UserActionsTest extends BaseTest {

    @AllureId("1721")
    @Test
    @DisplayName("Добавление товара в корзину")
    @Tag("Critical")
    @Story("Work with cart")
    @Component("Products Cart")
    void addToCartTest() {
        MainPage mainPage = open(baseUrl, MainPage.class);
        String itemImageSrc = mainPage.addItemInCart(0);

        mainPage.checkAddToCartButtonText(0, "В корзине")
                .checkProductsCountInCart("1");

        open(CartPage.URL, CartPage.class)
                .checkItemInCart(0, itemImageSrc);
    }

    @AllureId("1723")
    @Test
    @DisplayName("Удаление товара из корзины")
    @Story("Work with cart")
    @Component("Products Cart")
    void deleteFromCartTest() {
        CartPage cartPage = open(CartPage.URL, CartPage.class);
        sleep(3000);
        apiService.addItemToCart(prepareCookies());
        refresh();

        cartPage
                .deleteFromCart(0)
                .checkItemsListEmpty();
    }

    @AllureId("1724")
    @Test
    @DisplayName("Добавление товара в избранное")
    @Story("Work with favourites")
    @Component("Favourites")
    void addToFavouritesTest() {
        MainPage mainPage = open(baseUrl, MainPage.class);
        String itemImageSrc = mainPage.addItemToFavourites(0);

        mainPage.checkProductsCountInFavourites("1");

        open(FavouritesPage.URL, FavouritesPage.class)
                .checkItemInFavourites(0, itemImageSrc);
    }

    @AllureId("1722")
    @Test
    @DisplayName("Удаление товара из избранного")
    @Story("Work with favourites")
    @Component("Favourites")
    void removeFromFavouritesTest() {
        FavouritesPage favouritesPage = open(FavouritesPage.URL, FavouritesPage.class);
        sleep(3000);
        apiService.addItemToFavourites(prepareCookies());
        refresh();

        favouritesPage.deleteFromFavourites(0)
                .checkItemsListEmpty();
    }
}
