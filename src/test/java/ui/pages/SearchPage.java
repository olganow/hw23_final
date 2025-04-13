package ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private SelenideElement
            further = $x("//*[text()='Далее']");

    private ElementsCollection
            titleCollection = $$("a[class='course-card__title']"),
            cardsCollection = $$(".course-cards__item");

    private final static String COURSE = "Junior QA / Инженер по тестированию ПО";

    @Step("Validate a search result page")
    public SearchPage validatSearchResultPage() {
        titleCollection.first().shouldHave(text(COURSE));
        return this;
    }

    @Step("Search result page has positive count")
    public SearchPage searchResultPageHasPositiveCount() {
        further.scrollIntoView(true);
        cardsCollection.shouldHave(sizeGreaterThan(0)).filterBy(visible).shouldHave(sizeGreaterThan(0));
        int searchRes = cardsCollection.filter(visible).size();
        Assertions.assertTrue(searchRes > 0, "Количество видимых элементов должно быть больше нуля, получено: " + searchRes);
        return this;
    }

    @Step("Validate search result")
    public SearchPage searchResult(String expectedText) {
        $x(String.format("//*[text()='%s']", expectedText))
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }


}