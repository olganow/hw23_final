package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ui.pages.CatalogPage;
import ui.pages.LoginPage;
import ui.pages.SearchPage;

import java.util.Map;

import static constants.Constants.BASE_URL;

public class UiTestBase {
    public static final String browserHost = System.getProperty("browserHost", "remote");
    CatalogPage catalogPage = new CatalogPage();
    SearchPage searchPage = new SearchPage();
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = BASE_URL;
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.timeout = 10000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://user1:1234@" + System.getProperty("remoteHost") + "/wd/hub";
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Configuration.browser = System.getProperty("browser");

        if (!Configuration.browser.equals("firefox")) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
        }

        Selenide.closeWebDriver();
    }
}