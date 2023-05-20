package hw07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdvancedSearchPage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "#all-words")
    private WebElement containsAllInput;

    @FindBy(how = How.CSS, using = "#exact-phrase")
    private WebElement exactPhraseInput;

    @FindBy(how = How.CSS, using = "#least-words")
    private WebElement leastOneWordInput;

    @FindBy(how = How.CSS, using = "#without-words")
    private WebElement withoutWordsInput;

    @FindBy(how = How.CSS, using = "#title-is")
    private WebElement titleContainsInput;

    @FindBy(how = How.CSS, using = "#author-is")
    private WebElement authorEditorInput;

    @FindBy(how = How.CSS, using = "#facet-start-year")
    private WebElement startYearInput;

    @FindBy(how = How.CSS, using = "#facet-end-year")
    private WebElement endYearInput;

    @FindBy(how = How.CSS, using = "#date-facet-mode")
    private WebElement dateModeSelectElement;

    @FindBy(how = How.CSS, using = "#results-only-access-checkbox-advanced")
    private WebElement previewContentCheckbox;

    @FindBy(how = How.CSS, using = "#submit-advanced-search")
    private WebElement searchButton;

    public AdvancedSearchPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(containsAllInput));
        wait.until(ExpectedConditions.visibilityOf(exactPhraseInput));
        wait.until(ExpectedConditions.visibilityOf(leastOneWordInput));
        wait.until(ExpectedConditions.visibilityOf(withoutWordsInput));
        wait.until(ExpectedConditions.visibilityOf(titleContainsInput));
        wait.until(ExpectedConditions.visibilityOf(authorEditorInput));
        wait.until(ExpectedConditions.visibilityOf(dateModeSelectElement));
        wait.until(ExpectedConditions.visibilityOf(startYearInput));
        wait.until(ExpectedConditions.visibilityOf(endYearInput));
        wait.until(ExpectedConditions.visibilityOf(previewContentCheckbox));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    public AdvancedSearchPage typeContainsAllWords(String allWords) {
        containsAllInput.sendKeys(allWords);
        return this;
    }

    public AdvancedSearchPage typeExactPhrase(String phrase) {
        exactPhraseInput.sendKeys(phrase);
        return this;
    }

    public AdvancedSearchPage typeContainsAtLeastOneWord(String leastOneWord) {
        leastOneWordInput.sendKeys(leastOneWord);
        return this;
    }

    public AdvancedSearchPage typeWithoutWords(String withoutWords) {
        withoutWordsInput.sendKeys(withoutWords);
        return this;
    }

    public AdvancedSearchPage typeTitleContains(String titleContains) {
        titleContainsInput.sendKeys(titleContains);
        return this;
    }

    public AdvancedSearchPage typeAuthorEditor(String authorEditor) {
        authorEditorInput.sendKeys(authorEditor);
        return this;
    }

    public AdvancedSearchPage selectDateModeByText(String modeText) {
        Select countrySelect = new Select(dateModeSelectElement);
        if (modeText.equals("between") || modeText.equals("in")) {
            countrySelect.selectByVisibleText(modeText);
            return this;
        } else {
            return null;
        }
    }

    public AdvancedSearchPage typeStartYear(String startYear) {
        startYearInput.sendKeys(startYear);
        return this;
    }

    public AdvancedSearchPage typeEndYear(String endYear) {
        endYearInput.sendKeys(endYear);
        return this;
    }


    public AdvancedSearchPage clickPreviewContentCheckbox() {
        previewContentCheckbox.click();
        return this;
    }

    public SearchPage clickSearch() {
        searchButton.click();
        return new SearchPage(driver);
    }
}
