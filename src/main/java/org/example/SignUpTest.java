package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

public class SignUpTest {

    WebDriver driver;

    @BeforeClass
    public void setPathVariable() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }

    @Test
    public void zipCodeShouldBeValid() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Не попали на страницу регистрации");
    }

    // zip code должен содержать 5 цифр
    @Test
    public void userShouldNotBeRegisteredIfZipCodeMoreThanFiveDigits() {
        driver.findElement(By.name("zip_code")).sendKeys("12356789");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "zip code не содержит 5 цифр");
    }

    // проверка успешной регистрации при валидных данных
    @Test
    public void signUpDataIsValid() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Ошибка авторизации");
    }

    // проверка на то, что поле First Name является обязательным
    @Test
    public void fieldFirstNameIsRequired() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "Поле First Name не прошло проверку на обязательность");
    }

    // проверка на то, что поле Email является обязательным
    @Test
    public void fieldEmailIsRequired() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "Поле Email не прошло проверку на обязательность");
    }

    // проверка на то, что поле Password является обязательным
    @Test
    public void fieldPasswordIsRequired() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "Поле Password не прошло проверку на обязательность");
    }

    // проверка на то, что поле Confirm Password является обязательным
    @Test
    public void fieldConfirmPasswordIsRequired() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "Поле Confirm Password не прошло проверку на обязательность");
    }

    // проверка на совпадение паролей
    @Test
    public void equalsPassword() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector(".error_message"));
        Assert.assertTrue(message.isDisplayed(), "Пароли не совпадают");
    }

    // проверка на скрытие пароля ("*") в поле Password
    @Test
    public void passwordIsHidden() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        WebElement password = driver.findElement(By.name("password1"));
        Assert.assertEquals(password.getAttribute("type"), "password");
    }

    // проверка на скрытие пароля ("*") в поле Confirm Password
    @Test
    public void confirmPasswordIsHidden() {
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.name("first_name")).sendKeys("Alex");
        driver.findElement(By.name("email")).sendKeys("alex@mail.ru");
        driver.findElement(By.name("password1")).sendKeys("123456789Zz");
        driver.findElement(By.name("password2")).sendKeys("123456789Zz");
        WebElement password = driver.findElement(By.name("password2"));
        Assert.assertEquals(password.getAttribute("type"), "password");
    }
}