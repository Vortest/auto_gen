import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class crawler {
    private WebDriver driver;
    private String baseUrl;


    @Before
    public void openBrowser() {
        baseUrl = "http://localhost/homePage.php";
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    @Test
    public void pageTitleAfterSearchShouldBeginWithDrupal() throws IOException {
        System.out.println("Getting test page...");
        System.out.print(driver.getPageSource());
    }

    @After
    public void destroy(){
        driver.quit();
    }
}
