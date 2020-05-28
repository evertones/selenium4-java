package org.evertones.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.ClientConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WebDriverFactory {

    public WebDriver createChromeInstance() {
        return new ChromeDriver(createChromeOptions().setBinary("/usr/local/bin/chromedriver"));
    }

    public RemoteWebDriver createRemoteChromeInstanceWithConfig() throws MalformedURLException{
        ClientConfig config = ClientConfig
                .defaultConfig()
                .connectionTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofMinutes(5));

        return (RemoteWebDriver) RemoteWebDriver.builder()
                .address(createSeleniumGridUrl())
                .oneOf(createChromeOptions())
                .config(config)
                .build();
    }

    public RemoteWebDriver createRemoteChromeInstance() throws MalformedURLException {
        return new RemoteWebDriver(createSeleniumGridUrl(), createChromeOptions());
    }

    private ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);

        return options;
    }

    private URL createSeleniumGridUrl() throws MalformedURLException {
        return new URL("http://0.0.0.0:4444");
    }

}
