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

        // Comma-separated whitelist of remote IPv4 addresses which are allowed to connect to ChromeDriver (empty == all)
        options.addArguments("--whitelisted-ips=''");

        // Option to run Chrome instance as headless
        options.addArguments("--headless");

        // Disables GPU hardware acceleration. Recommended to avoid headless Chrome from crashing (https://github.com/SeleniumHQ/docker-selenium/issues/520#issuecomment-315733085)
        options.addArguments("--disable-gpu");

        // Disables sandboxing. It's recommended by Selenium Docker to avoid headless Chrome from crashing (https://github.com/SeleniumHQ/docker-selenium/issues/520#issuecomment-315733085)
        options.addArguments("--no-sandbox");

        // Runs the render and plugins in the same process as the browser. Recommended to avoid headless Chorme from crashing (https://github.com/SeleniumHQ/selenium/issues/4961#issuecomment-363094968)
        options.addArguments("--single-process");

        // Uses a temporary folder to create anonymous shared memory files.
        // The /dev/shm partition is too small in certain VM environments, causing Chrome to fail or crash (see http://crbug.com/715363). Use this flag to work-around this issue (a temporary directory will always be used to create anonymous shared memory files).
        options.addArguments("--disable-dev-shm-usage");

        // Disables experimental options not yet exposed by ChromeOption API.
        options.setExperimentalOption("useAutomationExtension", false);

        return options;
    }

    private FirefoxOptions getFirefoxOptions() {
        return  new FirefoxOptions().setAcceptInsecureCerts(true);
    }

    private URL getSeleniumGridURL() throws MalformedURLException {
        return new URL("http://localhost:4444");
    }

}
