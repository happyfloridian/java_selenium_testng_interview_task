package com.webstaurantstore.pages;

import org.openqa.selenium.By;

import static com.webstaurantstore.utilities.BrowserActions.clickElement;
import static com.webstaurantstore.utilities.BrowserActions.isElementDisplayed;

public class CartPage extends Page{
    private final static By EMPTY_CART_BUTTON = By.cssSelector("button[class^='emptyCartButton']");

    private final static By EMPTY_CART_BUTTON_IN_NOTIFICATION = By.xpath("(//button[normalize-space()='Empty Cart'])[last()]");
    private final static By CART_IS_EMPTY_NOTIFICATION = By.className("empty-cart__text");
    private final static By ITEM_IN_CART = By.cssSelector("li[class^='cartItemWrapper']");

    public void emptyCart() {
        clickElement(EMPTY_CART_BUTTON);
        clickElement(EMPTY_CART_BUTTON_IN_NOTIFICATION);
    }

    public boolean isCartEmptyNotificationDisplayed() {
        return isElementDisplayed(CART_IS_EMPTY_NOTIFICATION);
    }

    public boolean isCartEmpty() {
        return !isElementDisplayed(ITEM_IN_CART);
    }
}
