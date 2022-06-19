package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DiscountTest {

    @BeforeMethod

    @Test
    public void discountLessTha20() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        driver.findElement(By.xpath("//b[normalize-space()='vladimir_stuart@582.64.sharelane.com']"));




        WebElement message = driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Ошибка авторизации");
    }

    // zip code должен содержать 5 цифр
    @Test
    public void zipCodeShouldHaveFiveDigits() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "zip code не содержит 5 цифр");
    }

}
