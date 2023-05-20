package hw07;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/david/CTU/SEM_6/SELENIUM/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.get("https://link.springer.com/");

        driver.close();

        SearchPage p = new MainPage(driver)
                .clickAcceptButton()
                .clickCogsButton()
                .clickAdvancedSettings()
                .typeContainsAllWords("Page Object Model")
                .typeContainsAtLeastOneWord("Sellenium Testing")
                .selectDateModeByText("in")
                .clickPreviewContentCheckbox()
                .typeStartYear("2023")
                .clickSearch()
                .clickArticlesFilterButton()
                .clickDatePublishedButton()
                .selectDateModeByText("in")
                .typeStartYear("2023")
                .clickSubmitButton();

        for (int i = 0; i < 4; i++) {
            ArticlePage article = p.getNthResult(i);

            System.out.println(article.getTitle());
            System.out.println(article.getDoi());
            System.out.println(article.getPublishedDate());
            System.out.println("");

            p = article.goBack();
        }
    }

}
