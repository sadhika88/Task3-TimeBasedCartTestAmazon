package Tests;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import BaseClass.BrowserSetup;
import Pages.ShoppingandAddtoCartPage;

public class Task3ExecutionTest extends BrowserSetup{
	ShoppingandAddtoCartPage cartpage;
	
	@BeforeTest
	public void  setupTest() {
		setup();
		cartpage=new ShoppingandAddtoCartPage(driver);
	}
	@Test
	public void ShoppingToCartPage() throws InterruptedException {
		String[] products= {"waterbottle","shoe","keyboard","T-shirt"};
		String userName="UserTest01";
		if(!cartpage.isValidTimeWindow()) {
			System.out.println("❌ Test is allowed only between 6 PM and 7 PM");
			takeScreenshot("InvalidTimeWindow");
			return;
		}
		if(!cartpage.isValidUserName(userName)) {
			System.out.println("❌ invalid UserName..");
			takeScreenshot("InvalidUserName");
			return;
		}
		cartpage.ShoppingandaddToCart(products);
		  takeScreenshot("AfterAddToCart"); 
		int total=cartpage.getTotalCartPrice();
		System.out.println();
		System.out.println(" ✔️ Total price:⟨₹⟩" +total);
		if(total>2000) {
			System.out.println("  ✔️ Test Passed:Total Price is more than ₹2000.");
			   takeScreenshot("TotalPriceAbove2000"); 
		}
		else {
			System.out.println(" ❌ Test Failed:Total price is less than ₹2000. ");
			   takeScreenshot("TotalPriceBelow2000"); 
		}
	}
public void takeScreenshot(String fileName) {
	 try {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File folder = new File("screenshots");
	        if (!folder.exists()) {
	            folder.mkdir();
	        }

	        String path = "screenshots/" + fileName + "_" + System.currentTimeMillis() + ".png";
	        FileHandler.copy(screenshot, new File(path));
	        System.out.println("📸 Screenshot saved: " + path);
	    } catch (Exception e) {
	        System.out.println("❌ Failed to capture screenshot: " + e.getMessage());
	    }
		
	}
@AfterTest
public void cleanup() {
	tearDown();
}

}
