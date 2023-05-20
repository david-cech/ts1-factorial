package hw07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private WebDriver driver;
    private WebDriverWait defaultWait;

    @FindBy(how = How.CSS, using = "body > section > div > div.cc-banner__footer > button.cc-button.cc-button--contrast.cc-banner__button.cc-banner__button-accept")
    private WebElement acceptButton;

    @FindBy(how = How.CSS, using = "div[class='cross-nav cross-nav--wide'] a[class='register-link flyout-caption']")
    private WebElement loginButton;

    @FindBy(how = How.CSS, using = "img[alt='Search Options']")
    private WebElement cogsButton;

    @FindBy(how = How.CSS, using = "#advanced-search-link")
    private WebElement advancedSearchButton;

    public MainPage(WebDriver driver) {
        defaultWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage clickLogin() {
        defaultWait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
        return new LoginPage(driver);
    }

    public MainPage clickCogsButton() {
        defaultWait.until(ExpectedConditions.visibilityOf(cogsButton));
        cogsButton.click();
        return this;
    }

    public MainPage clickAcceptButton() {
        defaultWait.until(ExpectedConditions.visibilityOf(acceptButton));
        acceptButton.click();
        return this;
    }

    public AdvancedSearchPage clickAdvancedSettings() {
        defaultWait.until(ExpectedConditions.visibilityOf(advancedSearchButton));
        advancedSearchButton.click();
        return new AdvancedSearchPage(this.driver);
    }

}
