package testNGScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataParamTest {
	WebDriver driver;
	@Parameters("browser")
	@BeforeTest()
	public void setUp(String strBrowser) {
		System.out.println("Broswer Name is :" +strBrowser);
		if(strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		//WebDriverManager.edgedriver().setup();
		//driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
				
	}
	//@Parameters({"strUser","strPwd"})
	@Test(dataProvider = "LoginData")
	public void LoginTest(String strUser, String strPwd) {
		driver.get("http://the-internet.herokuapp.com/login");
		driver.findElement(By.id("username")).sendKeys(strUser);
		driver.findElement(By.id("password")).sendKeys(strPwd);
		driver.findElement(By.xpath("//button[@class='radius']")).click();

	}
	
	@DataProvider(name = "LoginData")
	public Object[][] getData(){
		return new Object[][] {
			new Object[] {"testuser1", "welcome123"},
			new Object[] {"testuser2", "welcome123"},
			new Object[] {"tomsmith", "supersecretpassword!"},
			};
	}
	
	@AfterTest()
	public void tearDown() {
		driver.close();
	}
	
}
