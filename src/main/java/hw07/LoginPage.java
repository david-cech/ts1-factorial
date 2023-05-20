package hw07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#login-box-email")
    private WebElement emailInput;

    @FindBy(how = How.CSS, using = "#login-box-pw")
    private WebElement passwordInput;

    @FindBy(how = How.CSS, using = "#login-box > div > div.form-submit > button")
    private WebElement submitButton;
    //cechdav5
    //password 1

    public LoginPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        wait.until(ExpectedConditions.visibilityOf(submitButton));
    }

    public LoginPage typeEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage typePassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public MainPage clickSubmit() {
        submitButton.click();
        return new MainPage(driver);
    }
}
