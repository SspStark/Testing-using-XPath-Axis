import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class RatingTest {
    public static void main(String args[]){

        System.setProperty("webdriver.chrome.driver", "D:\\sspto\\Downloads1\\Softwares\\Chrome Driver\\chromedriver-win64\\chromedriver.exe");
        // Open the Chrome Browser
        WebDriver driver = new ChromeDriver();

        // Open the NxtTrendz login page
        driver.get("https://rahulnxttrendz.ccbp.tech/login");

        // Find and fill in the form fields
        WebElement usernameEl = driver.findElement(By.xpath("//input[@id='username']"));
        usernameEl.sendKeys("rahul");
        WebElement passwordEl = driver.findElement(By.xpath("//input[@id='password']"));
        passwordEl.sendKeys("rahul@2021");

        WebElement buttonEl = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonEl.submit();

        // Define the expected URL of the home page
        String homePageUrl=  "https://rahulnxttrendz.ccbp.tech/";

        // Wait for the expected URL to be loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(homePageUrl));

        // Get the current URL after login
        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.equals(homePageUrl)){
            System.out.println("Navigation to home page was successful!");
        }else{
            System.out.println("Navigation to home page failed!");
        }

        // Find and click on the "Shop Now" button
        WebElement shopNowButtonEl =
                driver.findElement(By.xpath("//button[@class='shop-now-button']"));
        shopNowButtonEl.click();

        // Define the expected URL of the products page
        String productsPageUrl = "https://rahulnxttrendz.ccbp.tech/products";

        // Get the current URL after navigating to the products page
        currentUrl = driver.getCurrentUrl();

        if(currentUrl.equals(productsPageUrl)){
            System.out.println("Navigation to products page was successful!");
        }

        //Wait until elements are displayed
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='product-item']")));

        // Verify the display of products on the products page
        List<WebElement> products = driver.findElements(By.xpath("//li[@class='product-item']"));

        if (products.size() > 0) {
            System.out.println("Products are displayed successfully on the products page: "+ products.size());

            String expectedRating = "3.9";
            WebElement productRatingEle = driver.findElement(By.xpath("//h1[text()='Minifigures']/following-sibling::div//p[@class='rating']"));
            String rating = productRatingEle.getText();

            if(expectedRating.equals(rating)){
                System.out.println("Rating is 3.9");
            }

        } else {
            System.out.println("No products found on the products page.");
        }

        // Close the browser
        driver.quit();
    }
}
