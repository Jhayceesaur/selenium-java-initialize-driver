package rahulshettyacademy.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	
		public WebDriver initializeDriver() throws IOException
		{
			
			//Properties class
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/rahulshettyacademy/resources/GlobalData.properties");
			prop.load(fis); //Load file function
			String browserName = prop.getProperty("browser"); //Get values set in the global properties
			
			if (browserName.equalsIgnoreCase("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
		        options.addArguments("--incognito");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options);
			}
			
			else if (browserName.equalsIgnoreCase("firefox"))
			{
				//Firefox
			}
			
			else if (browserName.equalsIgnoreCase("edge"))
			{
				//Edge
				System.setProperty("webdriver.edge.driver", "edge.exe");
				driver = new EdgeDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			return driver;
		}
		
		public LandingPage launchApplication() throws IOException
		{
			driver = initializeDriver();
			LandingPage landingPage = new LandingPage(driver);
			landingPage.goTo();
			return landingPage;
		}
}
