package Pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.io.Files;

public class ShoppingandAddtoCartPage {
	WebDriver driver;
	//WebDriverWait wait;
	public ShoppingandAddtoCartPage(WebDriver driver) {
		this.driver=driver;
		//wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void ShoppingandaddToCart(String[] products) throws InterruptedException {
		for(String product:products) {
			driver.get("https://www.amazon.in/");
			WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
			searchBox.clear();
			searchBox.sendKeys(product);
			searchBox.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			List<WebElement> results=driver.findElements(By.cssSelector("span.a-price-whole"));
			if(!results.isEmpty()) {
				results.get(0).click();
				
				for(String tab:driver.getWindowHandles()) {
					driver.switchTo().window(tab);
				}
				try {
                    driver.findElement(By.id("add-to-cart-button")).click();
                } catch (Exception e) {
                    System.out.println("Add to cart button not found for: " + product);
                }

			}
			
		}
	
	}public int getTotalCartPrice() {
		driver.get("https://www.amazon.in/gp/cart/view.html");
		try {
		 WebElement total = driver.findElement(By.xpath("//*[@data-name='Subtotals']"));
		 String priceText=total.getText().replaceAll("[^0-9]", "");
		 return Integer.parseInt(priceText);
		}catch(Exception e) {
			System.out.println(" ❌  Could not get total price. ");
			return 0;
		}
	}
	public boolean isValidUserName(String name) {
		return name.length()==10&&name.matches("^[a-zA-Z0-9]*$");
	}
	public boolean isValidTimeWindow() {
		java.time.LocalTime now=java.time.LocalTime.now();
		java.time.LocalTime start=java.time.LocalTime.of(18,0);
		java.time.LocalTime end=java.time.LocalTime.of(19,0);
		return now.isAfter(start)&&now.isBefore(end);
		
	}


	

	
	

		 
		

	
	
}


		
		
		


	

