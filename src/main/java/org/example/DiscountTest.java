package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


//           # 20 - 49  | 2
//           # 50 - 99  | 3
//           # 100 - 499| 4
//           # 500 - 999 | 5
//           # 1000 - 4999  | 6
//           # 5000 - 9999  | 7
//           # 10000 and more | 8
public class DiscountTest extends BaseTest {
    String email;
    String password;

    public void login() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        email = driver.findElement(By.xpath("//td[text()='Email']/following::b")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password']/following::td")).getText();
        driver.findElement(By.cssSelector("img[src='../images/logo.jpg']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[value='Login']")).click();
    }

    @Test
    public void discountLessThanTwentyBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("19");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='0']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForTwentyBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("20");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='2']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForFiftyBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("50");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='3']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForHundredBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("100");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='4']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForFiveHundredBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("500");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='5']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForThousandBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("1000");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='6']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForFiveThousandBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("5000");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='7']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

    @Test
    public void discountForTenThousandBooks() {
        login();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=1");
        driver.findElement(By.cssSelector("img[src='../images/add_to_cart.gif']")).click();
        driver.findElement(By.cssSelector("a[href='./shopping_cart.py']")).click();
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("10000");
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='8']"));
        Assert.assertTrue(discount.isDisplayed(), "discount,% рассчитан неверно");
    }

}