package org.evertones.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    public WebDriver createChromeInstance() {
        return new ChromeDriver(getChromeOptions().setBinary("/usr/local/bin/chromedriver"));
    }

    public RemoteWebDriver createRemoteChromeInstance() throws MalformedURLException {
        return new RemoteWebDriver(getSeleniumGridURL(), getChromeOptions());
    }

    public WebDriver createFirefoxInstance() {
        FirefoxOptions options = new FirefoxOptions().setAcceptInsecureCerts(true);
        return new FirefoxDriver(options);
    }

    public RemoteWebDriver createRemoteFirefoxInstance() throws MalformedURLException {
        return new RemoteWebDriver(getSeleniumGridURL(), getFirefoxOptions());
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        return  new FirefoxOptions().setAcceptInsecureCerts(true);
    }

    private URL getSeleniumGridURL() throws MalformedURLException {
        return new URL("http://localhost:4444");
    }

}
