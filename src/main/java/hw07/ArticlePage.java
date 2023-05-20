package hw07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ArticlePage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#article-info-content > div > div:nth-child(2) > ul.c-bibliographic-information__list > li.c-bibliographic-information__list-item.c-bibliographic-information__list-item--doi > p > span.c-bibliographic-information__value")
    private WebElement doi;

    @FindBy(how = How.CSS, using = "#main-content > main > article > div.c-article-header > header > h1")
    private WebElement title;

    public ArticlePage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(doi));
        wait.until(ExpectedConditions.visibilityOf(title));
    }

    public String getTitle() {
        return title.getText();
    }

    public String getPublishedDate() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#article-info-content > div > div:nth-child(2) > ul"))));
        List<WebElement> bibData = driver.findElements(By.cssSelector("#article-info-content > div > div:nth-child(2) > ul > li"));

        for (WebElement bib : bibData) {
            List<WebElement> elements = bib.findElements(By.cssSelector("p"));
            if (elements.size() > 0 && elements.get(0).getText().contains("Published")) {
                WebElement publishedDate = bib.findElement(By.cssSelector("time"));
                return publishedDate.getText();
            }
        }
        return "";
    }

    public String getDoi() {
        return doi.getText();
    }

    public SearchPage goBack() {
        driver.navigate().back();
        return new SearchPage(driver);
    }
}
