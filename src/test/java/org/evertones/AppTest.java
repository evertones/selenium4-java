package org.evertones;

import org.evertones.selenium.WebDriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.List;

public class AppTest {

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver webDriver = null;


    @Before
    public void setUp() throws MalformedURLException {
        webDriver = webDriverFactory.createRemoteChromeInstanceWithConfig();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }

    @Test
    public void test() {
        webDriver.get("https://www.google.com");
        WebElement input = webDriver.findElement(By.cssSelector("input[name='q']"));
        input.sendKeys("test");
        input.sendKeys(Keys.ENTER);

        List<WebElement> searchResults = webDriver.findElements(By.id("result-stats"));

        Assert.assertTrue(!searchResults.isEmpty());
    }

}
