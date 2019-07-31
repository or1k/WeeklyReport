package Pages;

import View.Window;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

   private SelenideElement emailField = $(By.xpath("//input[@id='username']"));
    private SelenideElement buttonNext = $(By.xpath("//button[@id='login-submit']"));
    private SelenideElement passwordField = $(By.xpath("//input[@id='password']"));

    public SelenideElement getEmailField() {
        return emailField;
    }

    public SelenideElement getButtonNext() {
        return buttonNext;
    }

    public SelenideElement getPasswordField() {
        return passwordField;
    }

    public ReportPage login(){
        getEmailField().val(Window.userText.getText());
        getButtonNext().click();
        getPasswordField().val(String.valueOf(Window.passwordText.getPassword()));
        getButtonNext().click();
        return page(ReportPage.class);
    }

}
