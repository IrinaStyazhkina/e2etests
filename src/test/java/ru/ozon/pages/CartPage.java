package ru.ozon.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.ozon.utils.TestUtils.getImageNameFromSrc;

public class CartPage {

    public static final String URL = "cart";

    private SelenideElement productList = $("#split-Main-0");

    @Step("Check added item exists in cart")
    public CartPage checkItemInCart(int index, String itemImageSrc) {
        String src = productList.$("a img", index).getAttribute("src");
        assertEquals(itemImageSrc, getImageNameFromSrc(requireNonNull(src)));
        return this;
    }

    @Step("Delete item")
    public CartPage deleteFromCart(int index) {
        productList.$(byText("Удалить"), index).click();
        $$("[data-test-id='modal-container'] button").find(text("Удалить")).click();
        return this;
    }

    @Step("Check that items list is empty")
    public CartPage checkItemsListEmpty() {
        $("h1").shouldHave(text("Корзина пуста"));
        return this;
    }


}
