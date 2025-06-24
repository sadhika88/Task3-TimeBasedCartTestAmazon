package BaseClass;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserSetup {
	public static WebDriver driver;
	public static  void setup() {
		driver = new ChromeDriver();

         driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
	}
	public static void tearDown()

	{
		if (driver != null) {
			driver.quit();
		}
	}
}
