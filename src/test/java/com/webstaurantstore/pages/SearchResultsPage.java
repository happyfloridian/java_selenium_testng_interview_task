package com.webstaurantstore.pages;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.webstaurantstore.utilities.BrowserActions.*;
import static com.webstaurantstore.utilities.BrowserActions.clickElement;

public class SearchResultsPage extends Page{
    private final By PRODUCT_TITLES_DISPLAYED_ON_PAGE = By.cssSelector("a[data-testid='itemDescription']");
    private final By LAST_PAGE_NUMBER = By.cssSelector("a[aria-label*='last page']");
    private final By ADD_TO_CART_LAST_PRODUCT = By.xpath("(//div[@id='product_listing']//input[@name='addToCartButton'])[last()]");
    private final By VIEW_CART_BUTTON_IN_POPUP = By.xpath("//div[@id='watnotif-wrapper']//a[text()='View Cart']");

    private int getNumberOfResultPages() {
        String label = getAttributeValue(LAST_PAGE_NUMBER, "aria-label");
        if (label.contains("last page")) {
            //{9}
            return Integer.parseInt(label.split(", ")[1].replace("page ", ""));
        }
        return 1;
    }

    public List<String> getAllSearchResultTitles() {
        int pages = getNumberOfResultPages();
        String firstPageURL = getPageURL();

        List<String> resultsString = new ArrayList<>();
        resultsString.addAll(getElements(PRODUCT_TITLES_DISPLAYED_ON_PAGE).stream().map(element -> element.getText()).collect(Collectors.toList()));

        for (int i = 2; i <= pages; i++) {
            navigateTo(firstPageURL + "?page=" + i);
            resultsString.addAll(getElements(PRODUCT_TITLES_DISPLAYED_ON_PAGE).stream().map(element -> element.getText()).collect(Collectors.toList()));
        }
        return resultsString;

    }

    public boolean doAllSearchResultsContain(List<String> results, String searchItem) {
        for (String result : results) {
            if (!(result.contains(searchItem))) {
                return false;
            }
        }
        return true;
    }

    public void addLastProductToCart() {
        clickElement(ADD_TO_CART_LAST_PRODUCT);
    }

    public void openCart() {
        clickElement(VIEW_CART_BUTTON_IN_POPUP);
    }
}
