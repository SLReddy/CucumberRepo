package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStepDefinition  {

	public static WebDriver driver;
	
@After
	
	public void embedScreenshot(Scenario scenario) throws Exception {
	    if (scenario.isFailed()) {
	        try {
	            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	            String testName = scenario.getName();
	            scenario.embed(screenshot, "image/jpeg");
	            scenario.write(testName);
	        } catch (WebDriverException wde) {
	            System.err.println(wde.getMessage());
	        } catch (ClassCastException cce) {
	            cce.printStackTrace();}
	        }
	    
	    }



	@Given("^user is already on Login Page$")
	public void user_is_already_on_Login_Page() throws Throwable {

		System.setProperty("webdriver.chrome.driver", "C:/Users/Sreddy/Downloads/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://.ui.freecrm.com/");
		driver.manage().window().maximize();
		

	}

	@When("^title of login page is Free CRM$")
	public void title_of_login_page_is_Free_CRM() throws Throwable { 
		
		String title = driver.getTitle(); 
		System.out.println(title);
		Assert.assertEquals("Cogmento CRM", title);
		
	  
	  }
	

	@Then("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_and(String username, String password) throws Throwable {

		Thread.sleep(5000);
		 driver.findElement(By.name("email")).sendKeys(username);
		 driver.findElement(By.name("password")).sendKeys(password);
		 Thread.sleep(5000);
	}

	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() throws Throwable {
		
		driver.findElement(By.xpath("//*[@id='ui']/div/div/form/div/div[3]")).click();
		Thread.sleep(5000);

	}

	@Then("^user is on home page$")
	public void user_is_on_home_page() throws Throwable {

		String title = driver.getTitle();
		System.out.println("Home Page title ::"+ title);
		Assert.assertEquals("Cogmento CRM", title);
		
		driver.findElement(By.xpath("//*[text()='Contacts activity stream']")).isDisplayed();
		Thread.sleep(5000);
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws Throwable {

		driver.quit();
	}

}
