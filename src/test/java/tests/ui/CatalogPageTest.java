package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ui.pages.Locale;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.open;
import static constants.Constants.BASE_URL;
import static constants.Constants.CATALOG_URL;

@Feature("Stepik")
@Owner("Olganow")
@Link(value = "Testing", url = "https://github.com/olganow")
@Tag("web")
public class CatalogPageTest extends UiTestBase {
    String testDataOne = "Тестирование";

    @BeforeEach
    public void beforeEach() {
        open(BASE_URL + CATALOG_URL);
    }

    @Disabled("It is very simple test")
    @Test
    @DisplayName("Check QA courses")
    void cambridgeSearchOneWordTest() {
        catalogPage
                .validateCatalogPage()
                .setSearchData(testDataOne);
        searchPage.validatSearchResultPage();
    }

    @Story("Catalog tests")
    @Tag("actual")
    @ValueSource(strings = {"python", "java"})
    @ParameterizedTest
    @DisplayName("Check a number of recommendation on the first page for Stepik search for [test_data][0]")
    void cambridgeSearchTwoWordTest(String testData) {
        catalogPage
                .validateCatalogPage()
                .setSearchData(testData);
        searchPage.searchResultPageHasPositiveCount();
    }

    @Story("Catalog tests")
    @Tag("actual")
    @CsvSource(value = {
            "python, Асинхронный Python",
            "java,  Объектно-ориентированное программирование  на Java"
    }
    )
    @ParameterizedTest
    @DisplayName("Check a number of Stepik search result for [test_data][0]")
    void cambridgeSearchTwoParametersTest(String testData, String expectedText) {
        catalogPage.validateCatalogPage();
        catalogPage.setSearchData(testData);
        searchPage.searchResult(expectedText);
    }


    static Stream<Arguments> stepikCheckLocaleTest() {
        Stream<Arguments> of = Stream.of(
                Arguments.of(Locale.RU, List.of("Каталог", "Преподавание")),
                Arguments.of(Locale.EN, List.of("Catalog", "Teaching")),
                Arguments.of(Locale.ES, List.of("Catalog", "Teaching")),
                Arguments.of(Locale.DE, List.of("Katalog", "Teaching"))
        );
        return of;
    }

    @Story("Catalog tests")
    @Tag("actual")
    @MethodSource("stepikCheckLocaleTest")
    @DisplayName("Check button names according locale on Stepik")
    @ParameterizedTest
    void stepikCheckLocaleTest(Locale locale, List<String> buttonsText) {
        catalogPage
                .changeLanguage(locale)
                .validateButtons(buttonsText);
    }
}