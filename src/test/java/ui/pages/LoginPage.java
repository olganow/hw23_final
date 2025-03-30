package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement
            header = $x("//*[@data-tab-name='login']"),
            emailInput = $("#id_login_email"),
            passwordInput = $("#id_login_password"),
            sendButton = $x("//*[@type='submit']"),
            socialTitleButton = $(".social-title"),
            alert = $x("//ul/li[@role]"),
            closeButton = $x("//*[@class='modal-dialog-top__close']/span");

    private final static String TITLE_HEADER = "Войти";
    private final static String ENTER_BUTTON = "Войти";
    private final static String SOCIAL_NET_LOGIN = "Или войдите через социальные сети";
    private final static String ALERT = "E-mail адрес и/или пароль не верны.";


    @Step("Validate a Title")
    public LoginPage validateTitle() {
        header.shouldHave(text(TITLE_HEADER));
        return this;
    }

    @Step("Validate Page Components")
    public LoginPage validatePageComponents() {
        emailInput.shouldBe(visible);
        passwordInput.shouldBe(visible);
        sendButton.shouldHave(text(ENTER_BUTTON));
        socialTitleButton.shouldHave(text(SOCIAL_NET_LOGIN));
        closeButton.shouldBe(visible);
        return this;
    }

    @Step("Set a Email")
    public LoginPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Clear the Email Field")
    public LoginPage clearEmail() {
        emailInput.clear();
        return this;
    }

    @Step("Set a Password")
    public LoginPage setPassword(String password) {
        passwordInput.setValue(password);
        return this;
    }

    @Step("Clear the Password Field")
    public LoginPage clearPassword() {
        passwordInput.clear();
        return this;
    }

    @Step("Validate Alert")
    public LoginPage validateAlert() {
        alert.shouldHave(text(ALERT));
        return this;
    }


    @Step("Click the Enter button")
    public LoginPage clickEnter() {
        sendButton.click();
        return this;
    }

    @Step("Close the Form")
    public LoginPage closeForm() {
        closeButton.click();
        return this;
    }

}
