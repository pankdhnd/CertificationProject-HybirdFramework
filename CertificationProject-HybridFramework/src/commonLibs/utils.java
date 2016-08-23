package commonLibs;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class utils {


 public static void waitForSeconds(long Seconds) {
  try {
   Thread.sleep(Seconds * 1000L);
  } catch (Exception e) {
	  //Intentionally empty
  }
 }

public static Properties getProperties (String propertyFileName){
	try {
		
		InputStream ofileReader;
		Properties oProperty;		
		
		ofileReader = new FileInputStream(propertyFileName);
		oProperty = new Properties();		
		oProperty.load(ofileReader);		
		return oProperty;		
	} catch (Exception e) {
		 System.out.println("utils->getProperties()->Error while reading config file; here is some more detail: ");
		 e.printStackTrace();
		 return null;	
	}
	
}
//---------------------------------------------------------------------------------------------------------------------------------------------	
//--------------------------------------------------------------------------------------------------------------------------------------------- 
public static By getLocatorBy(String locatorString){
	try {
		
		String[] Locator;
		locatorString = locatorString.trim();
		
//		if(locatorString.isEmpty() || locatorString.contains(":=")){
//			throw new Exception ("utils->getLocatorBy()->Invalid locator");
//		}
		if(locatorString.isEmpty()){
			throw new Exception ("utils->getLocatorBy()->Invalid locator");
		}
		
		Locator = locatorString.split(":=");
		
		if (Locator[0].equalsIgnoreCase("id")){
			return By.id(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("class")){
			return By.className(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("xpath")){
			return By.xpath(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("css")){
			return By.cssSelector(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("link")){
			return By.linkText(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("partiallink")){
			return By.partialLinkText(Locator[1]);				
		}
		
		if (Locator[0].equalsIgnoreCase("name")){
			return By.name(Locator[1]);
		}
		
		if (Locator[0].equalsIgnoreCase("tagname")){
			return By.tagName(Locator[1]);
		}
		
		throw new Exception ("utils->getLocatorBy()->Invalid locator");		
	} catch (Exception e) {
		 System.out.println("utils->getLocatorBy()->Error while getting locator; here is some more detail: ");
		 e.printStackTrace();
		 return null;
	}
}
//---------------------------------------------------------------------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------------------------------------------------------------------
public static String getDateTimeStamp(){
	
	try {
		Date todaysDate;
		String[] dateInParts;
		String finalDate;
		
		todaysDate = new Date();
				
		dateInParts = todaysDate.toString().split(" ");		
		finalDate = dateInParts[1] + "_" + dateInParts[2] + "_" + dateInParts[5] + "_" + dateInParts[3];		
		finalDate=finalDate.replace(":", "_");		
		return finalDate;
	} catch (Exception e) {
		 System.out.println("utils->getDateTimeStamp()->Error while getting/converting date/time; here is some more detail: ");
		 e.printStackTrace();
		 return null;
	}
		
}

public static void highlightElement(WebDriver oDriver,WebElement Element){
//	WebElement element = oDriver.findElement(oBy);
	JavascriptExecutor js = (JavascriptExecutor) oDriver;
	js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", Element);
}


}//END CLASS