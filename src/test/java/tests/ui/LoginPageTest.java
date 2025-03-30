package tests.ui;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static constants.Constants.BASE_URL;
import static constants.Constants.CATALOG_URL;

@Feature("Stepik")
@Owner("Olganow")
@Link(value = "Testing", url = "https://github.com/olganow")
@Tag("web")
public class LoginPageTest extends TestBase {
    private String email = "test@test.com";
    private String password = "123123Qw!";


    @BeforeEach
    public void beforeEach() {
        open(BASE_URL + CATALOG_URL);
    }

    @Test
    @Story("Login page")
    @DisplayName("Validate Authorisation Form")
    void validateAuthorisationFormTest() {
        catalogPage.clickLogin();
        loginPage
                .validateTitle()
                .validatePageComponents();
    }

    @Test
    @Story("Login")
    @DisplayName("Cancel Authorisation")
    void cancelAuthorisationTest() {
        catalogPage.clickLogin();
        loginPage
                .validateTitle()
                .setEmail(email)
                .setPassword(password)
                .clickEnter()
                .validateAlert()
                .clearEmail()
                .clearPassword()
                .closeForm();
        catalogPage.validateCatalogPage();
    }

}