package testNGScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleFourTest {
	WebDriver driver;
	//@BeforeMethod
	@BeforeTest()
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//WebDriverManager.edgedriver().setup();
		//driver = new EdgeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 3000)
	public void searchAppiumTest() {
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
