package hw07;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

public class SeleniumTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/david/CTU/SEM_6/SELENIUM/chromedriver");

        driver = new ChromeDriver();

        driver.get("https://link.springer.com/");
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
    }

    @ParameterizedTest
    @MethodSource("articleTestProvider")
    public void articleTest(String email, String password, String articleTitle, String doi, String publishedDate) {
        ArticlePage p = new MainPage(driver)
                .clickAcceptButton()
                .clickLogin()
                .typeEmail(email)
                .typePassword(password)
                .clickSubmit()
                .clickCogsButton()
                .clickAdvancedSettings()
                .typeTitleContains(articleTitle)
                .clickPreviewContentCheckbox()
                .clickSearch()
                .getNthResult(0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(doi, p.getDoi()),
                () -> Assertions.assertEquals(publishedDate, p.getPublishedDate())
        );
    }

    private static Stream<Arguments> articleTestProvider() {
        return Stream.of(
                Arguments.of("cechdav5@fel.cvut.cz", "password1", "An approach to predict the task efficiency of web pages", "https://doi.org/10.1007/s11042-023-14619-3", "16 February 2023"),
                Arguments.of("cechdav5@fel.cvut.cz", "password1", "MUTTA: a novel tool for E2E web mutation testing", "https://doi.org/10.1007/s11219-023-09616-6", "17 April 2023"),
                Arguments.of("cechdav5@fel.cvut.cz", "password1", "Testing locus coeruleus-norepinephrine accounts of working memory, attention control, and fluid intelligence", "https://doi.org/10.3758/s13415-023-01096-2", "20 April 2023"),
                Arguments.of("cechdav5@fel.cvut.cz", "password1", "Object memory is multisensory: Task-irrelevant sounds improve recollection", "https://doi.org/10.3758/s13423-022-02182-1", "27 September 2022")
        );
    }
}
