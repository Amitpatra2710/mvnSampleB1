package testNGScripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataTest {
	WebDriver driver;
	Properties prop;
	//@Parameters("browser")
	@BeforeTest()
	public void setUpProp() {
		prop = new Properties();
		String path = System.getProperty("user.dir")
				+"//src//test//resources//configFiles//config.properties";
		FileInputStream fin;
		try {
			fin = new FileInputStream(path);
			prop.load(fin);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		@BeforeMethod
		public void setUp() {
		String strBrowser = prop.getProperty("broswer");
		if(strBrowser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(strBrowser.equalsIgnoreCase("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		}
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\Ajay Patra\\Desktop\\Materials\\Driver\\chromedriver_win32\\chromedriver.exe");
		//WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		//WebDriverManager.edgedriver().setup();
		//driver = new EdgeDriver();

				
	//@Parameters({"strUser","strPwd"})
	//@Test(dataProvider = "LoginData")
	@Test()
	public void LoginTest() throws IOException {
		driver.get(prop.getProperty("url"));
		driver.findElement(By.id("username")).sendKeys(readData("userID_Value"));
		driver.findElement(By.id("password")).sendKeys(readData("pwd_Value"));
		//System.out.println(prop.getProperty("loginBtn"));
		driver.findElement(By.xpath(prop.getProperty("loginBtn"))).click();
		
		

	}
	
	public static String readData(String colName) throws IOException {
		String colValue="";
		String path = System.getProperty("user.dir")
				+"//src//test//resources//dataFiles//loginFile.xlsx";
		FileInputStream fin = new FileInputStream(path);
		//.xlsx
		XSSFWorkbook workbook = new XSSFWorkbook(fin);
		//.xls - HSSFWorkBook
		XSSFSheet sheet = workbook.getSheet("loginPage");
		int numRows = sheet.getLastRowNum();
		for(int i=1; i <= numRows; i++) {
			XSSFRow row = sheet.getRow(i);
			if(row.getCell(0).getStringCellValue().equalsIgnoreCase(colName)) {
				colValue = row.getCell(1).getStringCellValue();
			}
		}
		System.out.println(colValue);
		return colValue;
	}
	
	//@DataProvider(name = "LoginData")
	
	//public Object[][] getData(){
		//return new Object[][] {
			//new Object[] {"testuser1", "welcome123"},
			//new Object[] {"testuser2", "welcome123"},
			//new Object[] {"tomsmith", "supersecretpassword!"},
			//};
	//}
	
	@DataProvider(name = "LoginData")
	public Object[][] getData() throws CsvValidationException, IOException{
		String path = System.getProperty("user.dir")
				+"//src//test//resources//dataFiles//loginData.csv";

			CSVReader reader = new CSVReader(new FileReader(path));
		String col[];
		ArrayList<Object> dataList = new ArrayList<Object>();
		while((col= reader.readNext()) != null) {
			
			Object[] record = {col[0], col[1]};
			dataList.add(record);
		}
		System.out.println("No. of Records :" +dataList.size());
		return dataList.toArray(new Object[dataList.size()][]);

		}
	
	@AfterTest()
	public void tearDown() {
		driver.close();
	}
}
