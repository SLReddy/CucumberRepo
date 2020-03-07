package stepDefinitions;

import java.io.File;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;



import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;


public class ServiceHooks {

WebDriver driver;

	@Before
	public void initializeTest() {

	}

	@After
	
	public void embedScreenshot(Scenario scenario) throws Exception {
	    if (scenario.isFailed()) {
	        try {
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            String testName = scenario.getName();
	            scenario.embed(screenshot, "image/png");
	            scenario.write(testName);
	        } catch (WebDriverException wde) {
	            System.err.println(wde.getMessage());
	        } catch (ClassCastException cce) {
	            cce.printStackTrace();}
	        }
	    }
	} 
