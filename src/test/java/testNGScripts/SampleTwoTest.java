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

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTwoTest {
	WebDriver driver;
	//@BeforeMethod
	//@BeforeTest()
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		
	}
	@Test(groups = {"featureOne"})
	public void searchCyprassTest() {
		WebDriverManager.chromedriver().setup();;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Cyprass Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Cyprass Tutorial - Google Search");
		driver.close();
	}

	@Test(groups = {"featureThree"})
	public void searchPythonTest() {
		WebDriverManager.chromedriver().setup();;
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Python Tutorial");
		searchBox.submit();
		Assert.assertEquals(driver.getTitle(), "Python Tutorial - Google Search");
		driver.close();
	}
	
	//@AfterMethod()
	//@AfterTest()
	public void tearDown() {
		driver.close();
	}
}
