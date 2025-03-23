package pages;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private SelenideElement
            header = $x("//*[text()='Онлайн-курсы']");


    public SearchPage validatSearchResultPage() {
        $$("a[class='course-card__title']").first()
                .shouldHave(text("Junior QA / Инженер по тестированию ПО"));
        return this;
    }

    public SearchPage searchResultPageEqual24() {
        $x("//*[text()='Далее']").scrollIntoView(true);
        int searchRes = $$(".course-cards__item").size();
        Assertions.assertEquals(21, searchRes);
        return this;
    }

    public SearchPage searchResult(String expectedText) {
        $x(String.format("//*[text()='%s']", expectedText)).shouldHave(text(expectedText));
        return this;
    }


}