package ru.ozon.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.ozon.elements.LoginPopupIframe;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static java.util.Objects.requireNonNull;
import static ru.ozon.utils.TestUtils.getImageNameFromSrc;

public class MainPage {

    private SelenideElement loginButton = $("[data-widget='profileMenuAnonymous'] svg"),
            itemsLine = $("[data-widget='skuLine']");

    private ElementsCollection addToCartButtons = itemsLine.$$("[type='addToCartButton']");

    @Step("Click login button")
    public LoginPopupIframe clickLoginButton() {
        loginButton.click();
        switchTo().frame($("#authFrame"));
        return new LoginPopupIframe();
    }

    @Step("Add item into cart")
    public String addItemInCart(int index) {
        String itemImageSrc = itemsLine.$("img", index).getAttribute("src");
        checkNoPopupVisible();
        addToCartButtons.get(index).click();
        return getImageNameFromSrc(requireNonNull(itemImageSrc));
    }

    @Step("Check that 'Add to cart' button has text: {value}")
    public MainPage checkAddToCartButtonText(int index, String value) {
        addToCartButtons.get(index).shouldHave(text(value));
        return this;
    }

    @Step("Check that there are {count} items in cart")
    public MainPage checkProductsCountInCart(String count) {
        $("a[href='/cart'] span").shouldHave(text(count));
        return this;
    }

    @Step("Add item to favourites")
    public String addItemToFavourites(int index) {
        String itemImageSrc = itemsLine.$("img", index).getAttribute("src");
        checkNoPopupVisible();
        itemsLine.$("img", index).closest("a").preceding(0).click();
        return getImageNameFromSrc(requireNonNull(itemImageSrc));
    }

    public MainPage checkProductsCountInFavourites(String count) {
        $("a[href='/my/favorites']").shouldHave(text(count));
        return this;
    }

    private void checkNoPopupVisible() {
        if ($("div[data-widget='alertPopup']").has(text("Вы находитесь в зоне очень быстрой доставки!"))) {
            $("[data-widget='alertPopup'] button").click();
        }
    }
}
