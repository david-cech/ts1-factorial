package hw07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#content-type-facet > ol > li:nth-child(1)")
    private WebElement articlesFilterButton;

    @FindBy(how = How.CSS, using = "#date-facet-submit")
    private WebElement submitButton;

    @FindBy(how = How.CSS, using = "#date-facet > button")
    private WebElement datePublishedButton;

    @FindBy(how = How.CSS, using = "#date-facet-mode")
    private WebElement dateModeSelectElement;

    @FindBy(how = How.CSS, using = "#start-year")
    private WebElement startYearInput;

    public SearchPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(articlesFilterButton));
        wait.until(ExpectedConditions.visibilityOf(datePublishedButton));
    }

    public SearchPage clickArticlesFilterButton() {
        articlesFilterButton.click();
        return this;
    }

    public SearchPage clickSubmitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(submitButton));

        submitButton.click();
        return this;
    }

    public SearchPage clickDatePublishedButton() {
        datePublishedButton.click();
        return this;
    }

    public SearchPage selectDateModeByText(String modeText) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(dateModeSelectElement));
        Select countrySelect = new Select(dateModeSelectElement);
        if (modeText.equals("between") || modeText.equals("in")) {
            countrySelect.selectByVisibleText(modeText);
            return this;
        } else {
            return null;
        }
    }

    public SearchPage typeStartYear(String year) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(startYearInput));
        startYearInput.clear();
        startYearInput.sendKeys(year);
        return this;
    }

    public ArticlePage getNthResult(int n) {
        driver.findElement(By.cssSelector("#results-list > li:nth-child(" + (n + 1) + ") a")).click();
        //allResults.get(n).findElement(By.cssSelector("a")).click();
        return new ArticlePage(driver);
    }
}
