package com.webstaurantstore.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserActions {
    private BrowserActions() {}

    public static WebElement getElement(By element) {
        return Driver.getDriver().findElement(element);
    }

    public static List<WebElement> getElements(By element) {
        return Driver.getDriver().findElements(element);
    }

    public static void clickElement(By element) {
        getElement(element).click();
    }

    public static void sendKeys(By element, String input) {
        getElement(element).sendKeys(input);
    }

    public static void navigateTo(String url) {
        Driver.getDriver().get(url);
    }

    public static void quitDriver(){
        Driver.closeDriver();
    }

    public static String getPageURL() {
        return Driver.getDriver().getCurrentUrl();
    }

    public static String getAttributeValue(By element, String attribute) {
        return getElement(element).getAttribute(attribute);
    }

    public static boolean isElementDisplayed(By element) {
        try {
            getElement(element);
        } catch (NotFoundException e) {
            return false;
        }
        return true;
    }


}
