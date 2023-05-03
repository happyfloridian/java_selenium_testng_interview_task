package com.webstaurantstore.tests;





import com.webstaurantstore.pages.CartPage;
import com.webstaurantstore.pages.HomePage;
import com.webstaurantstore.pages.SearchResultsPage;
import com.webstaurantstore.utilities.Driver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.webstaurantstore.utilities.BrowserActions.navigateTo;
import static com.webstaurantstore.utilities.BrowserActions.quitDriver;

public class SearchTest {

    private final HomePage homePage = new HomePage();
    private final SearchResultsPage searchResultsPage = new SearchResultsPage();
    private final CartPage cartPage = new CartPage();

    private SoftAssert softAssert = new SoftAssert();


    //Go to https://www.webstaurantstore.com/
    @BeforeMethod
    public void setUp(){
        navigateTo("https://www.webstaurantstore.com/");
    }

    @AfterMethod
    public void tearDown(){
        quitDriver();
        softAssert.assertAll();
    }

    @Test
    public void testCaseTask(){
        //Search for 'stainless work table'.
        String searchItem = "stainless work table";
        homePage.searchForItem(searchItem);

        //Check the search result ensuring every product has the word 'Table' in its title.
        List<String> results = searchResultsPage.getAllSearchResultTitles();
        String word = "Table";
        softAssert.assertTrue(searchResultsPage.doAllSearchResultsContain(results, word), "Not all search results contain the word " + word);

        //Add the last of found items to Cart.
        searchResultsPage.addLastProductToCart();
        searchResultsPage.openCart();
        softAssert.assertFalse(cartPage.isCartEmpty(), "No items found in Cart!");

        //Empty Cart.
        cartPage.emptyCart();
        softAssert.assertTrue(cartPage.isCartEmptyNotificationDisplayed(), "Card is empty notification not displayed!");
    }

}
