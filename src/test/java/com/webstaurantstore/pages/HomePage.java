package com.webstaurantstore.pages;

import org.openqa.selenium.By;

import static com.webstaurantstore.utilities.BrowserActions.clickElement;
import static com.webstaurantstore.utilities.BrowserActions.sendKeys;

public class HomePage extends Page{
    private final static By SEARCH_BOX = By.id("searchval");
    private final static By SEARCH_BUTTON = By.cssSelector("button[value='Search']");

    public void searchForItem(String searchItem) {
        sendKeys(SEARCH_BOX, searchItem);
        clickElement(SEARCH_BUTTON);
    }
}
