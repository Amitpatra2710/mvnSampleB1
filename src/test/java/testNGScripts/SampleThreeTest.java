package testNGScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleThreeTest {
	WebDriver driver;
	//@BeforeMethod
	//@BeforeTest()
	//public void setUp() {
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		//WebDriverManager.edgedriver().setup();
		//driver = new EdgeDriver();
		//driver.manage().window().maximize();
		
	//}
	@Test
	public void searchJavaTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(driver.getTitle(), "Google");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
		//softassert.assertAll();
		long id = Thread.currentThread().getId();
		System.out.println("Current Thread is :"+id);
		driver.close();
	}
	
	@Test
	public void searchRFWTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(driver.getTitle(), "Google");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Robot Framework Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Robot Framework Tutorial - Google Search");
		softassert.assertAll();
		long id = Thread.currentThread().getId();
		System.out.println("Current Thread is :"+id);
		driver.close();
	}

	@Test
	public void searchSeleniumTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Selenium Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Selenium Tutorial - Google");
		long id = Thread.currentThread().getId();
		System.out.println("Current Thread is :"+id);
		driver.close();
	}
	
	@Test
	public void searchAppiumTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Appium Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Appium Tutorial - Google Search");
		long id = Thread.currentThread().getId();
		System.out.println("Current Thread is :"+id);
		driver.close();
		
	}
	
	//@AfterMethod()
	//@AfterTest()
	//public void tearDown() {
		//driver.close();
	//}
}
