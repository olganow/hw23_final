package ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private SelenideElement
            header = $x("//*[text()='Онлайн-курсы']");


    public SearchPage validatSearchResultPage() {
        $$("a[class='course-card__title']").first()
                .shouldHave(text("Junior QA / Инженер по тестированию ПО"));
        return this;
    }

    public SearchPage searchResultPageHasPositiveCount() {
        $x("//*[text()='Далее']").scrollIntoView(true);
        $$(".course-cards__item").shouldHave(sizeGreaterThan(0)).filterBy(visible).shouldHave(sizeGreaterThan(0));
        int searchRes = $$(".course-cards__item").filter(visible).size();
        Assertions.assertTrue(searchRes > 0, "Количество видимых элементов должно быть больше нуля, получено: " + searchRes);
        return this;
    }

    public SearchPage searchResult(String expectedText) {
        $x(String.format("//*[text()='%s']", expectedText))
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }


}