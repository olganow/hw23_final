package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CatalogPage;
import pages.Locale;
import pages.SearchPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.BASE_URL;


public class StepikWebTest extends TestBase {
    String testDataOne = "Тестирование";
    CatalogPage catalogPage = new CatalogPage();
    SearchPage searchPage = new SearchPage();

    @BeforeEach
    public void beforeEach() {
        open(BASE_URL);
    }

    @Disabled("It is very simple test")
    @Test
    @DisplayName("Check QA courses")
    void cambridgeSearchOneWordTest() {
        catalogPage.validateCatalogPage();
        catalogPage.setSearchData(testDataOne);
        searchPage.validatSearchResultPage();
    }

    @ValueSource(strings = {"python", "java"})
    @ParameterizedTest
    @DisplayName("Check a number of recommendation on the first page for Stepik search for [test_data][0]")
    void cambridgeSearchTwoWordTest(String testData) {
        catalogPage.validateCatalogPage();
        catalogPage.setSearchData(testData);
        searchPage.searchResultPageEqual24();
    }


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

    @MethodSource("stepikCheckLocaleTest")
    @DisplayName("Check button names according locale on Stepik")
    @ParameterizedTest
    void stepikCheckLocaleTest(Locale locale, List<String> buttonsText) {
        catalogPage.changeLanguage(locale);
        catalogPage.validateButtons(buttonsText);
    }
}