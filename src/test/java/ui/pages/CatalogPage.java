package ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    private SelenideElement
            header = $x("//*[text()='Онлайн-курсы']"),
            searchInput = $(".search-form__input"),
            emailButton = $x("//*[text()='help@stepik.org']"),
            changeLanguageButton = $x("//*[@class='drop-down drop-down-menu ember-view language-selector']//button/span"),
            login = $x("//*[text()='Войти']");;


    private final static String COURSE_TITLE = "Онлайн-курсы";
    private final static String ENTER = "Войти";

    public CatalogPage validateCatalogPage() {
        header.shouldHave(text(COURSE_TITLE));
        return this;
    }

    public CatalogPage setSearchData(String testData) {
        searchInput.setValue(testData).pressEnter();
        return this;
    }

    public CatalogPage changeLanguage(Locale locale) {
        emailButton.scrollIntoView(true);
        changeLanguageButton.click();
        String expectedText = locale.getDesc();
        $x(String.format("//li/button[text()='%s']", expectedText)).scrollIntoView(true).click();
        return this;
    }

    public CatalogPage validateButtons(List<String> buttonsText) {
        $$(".navbar__menu-item").shouldHave(CollectionCondition.texts(buttonsText));
        return this;
    }


    @Step("Click Login")
    public CatalogPage clickLogin() {
        login.shouldHave(text(ENTER)).click();
        return this;
    }

}