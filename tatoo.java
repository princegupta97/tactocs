import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;

import org.openqa.selenium.interactions.Actions;
public class tatoo 
{
	public static void main(String[] args) 
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver","/home/princegupta/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		driver.findElement(By.linkText("Basic Course")).click();
		
		WebElement findGreen = driver.findElement(By.className("greenbox"));
		findGreen.click();
		driver.switchTo().frame("main");
	
		String color1 = driver.findElement(By.id("answer")).getAttribute("class");
		System.out.println(color1);
		boolean f = true;
		while(f)
		{
			
			driver.switchTo().frame("child");
			String color2 = driver.findElement(By.id("answer")).getAttribute("class");
			System.out.println(color2);
			if(color1.equals(color2))
			{
				f = false;
			}
			else {
				driver.switchTo().defaultContent();
				driver.switchTo().frame("main");
				driver.findElement(By.linkText("Repaint Box 2")).click();
				
			}
		}
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		driver.findElement(By.linkText("Proceed")).click();
		WebElement from = driver.findElement(By.xpath(".//*[@id='dragbox']"));
		WebElement to = driver.findElement(By.xpath(".//*[@id='dropbox']"));
		Actions builder = new Actions(driver);
		 
		Action dragAndDrop = builder.clickAndHold(from)
		 
		.moveToElement(to)
		 
		.release(to)
		 
		.build();
		 
		dragAndDrop.perform();
		driver.findElement(By.linkText("Proceed")).click();
		driver.findElement(By.linkText("Launch Popup Window")).click();
		
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window

		// Now you are in the popup window, perform necessary actions here
		WebElement send = driver.findElement(By.id("name"));
		send.sendKeys("Prince");
		driver.findElement(By.id("submit")).click();
		
		driver.switchTo().window(parentWindowHandler); 
		driver.findElement(By.linkText("Proceed")).click();
		driver.findElement(By.linkText("Generate Token")).click();
		String token = driver.findElement(By.cssSelector("#token")).getText();
		
		String split = token.substring(7);
		Cookie token1 = new Cookie("Token", split);
	    driver.manage().addCookie(token1);
		
	    driver.findElement(By.linkText("Proceed")).click();
	}
	

}


