package ru.ozon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ozon.utils.TestUtils.getImageNameFromSrc;

public class FavouritesPage {

    public static final String URL = "my/favorites";

    private SelenideElement productsContainer = $(".widget-search-result-container");


    @Step("Check added item exists in favourites")
    public FavouritesPage checkItemInFavourites(int index, String itemImageSrc) {
        String src = productsContainer.$("a img", index).getAttribute("src");
        assertEquals(itemImageSrc, getImageNameFromSrc(requireNonNull(src)));
        return this;
    }

    @Step("Delete item")
    public FavouritesPage deleteFromFavourites(int index) {
        productsContainer.shouldHave(text("Gillette Бритва для бритья"));
        $("div[align='topRight']", index).click();
        return this;
    }

    @Step("Check that favourites list is empty")
    public FavouritesPage checkItemsListEmpty() {
        refresh();
        $("[data-widget='paginator']").shouldHave(text("В Избранном пока ничего нет"));
        return this;
    }
}
