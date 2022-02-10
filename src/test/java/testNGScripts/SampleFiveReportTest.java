package testNGScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import commonUtls.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleFiveReportTest {
	WebDriver driver;
	ExtentReports reports;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	@BeforeTest
	public void setupReport() {
		reports = new ExtentReports();
		spark = new ExtentSparkReporter("target/ExtentReporter.html")
				.viewConfigurer()
				.viewOrder()
				.as(new ViewName[] {
						ViewName.DASHBOARD,
						ViewName.TEST,
						ViewName.AUTHOR,
						ViewName.DEVICE,
						ViewName.EXCEPTION,
						ViewName.LOG					
				}).apply();
		reports.attachReporter(spark);
	}
	
	@BeforeMethod
		//@BeforeTest()
		public void setUp() {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			//WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			driver.manage().window().maximize();
			
		}
		@Test
		public void searchJavaTest() {
			extentTest = reports.createTest("Search Java Test");
			driver.get("https://www.google.com");
			SoftAssert softassert = new SoftAssert();
			softassert.assertEquals(driver.getTitle(), "Google");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Java Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(), "Java Tutorial - Google Search");
			//softassert.assertAll();
		}
		
		@Test
		public void searchRFWTest() {
			extentTest = reports.createTest("Search RFW Test");
			driver.get("https://www.google.com");
			SoftAssert softassert = new SoftAssert();
			softassert.assertEquals(driver.getTitle(), "Googl");
			WebElement searchBox = driver.findElement(By.name("q"));
			searchBox.sendKeys("Robot Framework Tutorial");
			searchBox.submit();
			Assert.assertEquals(driver.getTitle(), "Robot Framework Tutoria - Google Search");

		}

		
		@AfterTest()	
		public void finishReports(){
			reports.flush();
		}
		
		@AfterMethod()
		//@AfterTest()
		
		public void tearDown(ITestResult result) {
			if(ITestResult.FAILURE == result.getStatus()) {
				String path = Utility.getScreenshotPath(driver);
				extentTest.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			driver.close();
		}
}
