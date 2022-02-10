package testNGScripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class HTMLUnitTest {
	//@Test
	public void headlessTest() {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		driver.quit();
	}
	
	@Test
	public void remoteDriverTest() throws MalformedURLException {
		ChromeOptions options = new ChromeOptions();
		options.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		String strHub="http://localhost:4444/";
		WebDriver driver = new RemoteWebDriver(new URL(strHub),options);
		//HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Java Tutorial");
		searchBox.submit();
		System.out.println("After search page title in remotetesting :"+driver.getTitle());
	}


}
