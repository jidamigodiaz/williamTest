package feature.steps;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import WebDriver.BrowserDriver;

public class Definitions {
	private static final Logger LOGGER = Logger.getLogger( BrowserDriver.class.getName() );
	private WebDriver driver;

	@Given("I am on the web page (.*)")
	public void openWebPage(String webPage) {
		System.out.println(webPage);
		driver = BrowserDriver.getCurrentDriver();
		driver.get(webPage);
	}
	
	@Then("I should see the web correctly charged")
	public void checkWebLoaded() {
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	@When("I log in the console the site version")
	public void logSiteVersion() {
		//I'm not sure about if this is the version number required
		String version = driver.findElement(By.id("root_site")).getCssValue("background").toString().split("v=|\"")[2];
		LOGGER.info("version: " + version);
	}
	
	@When("I log in the console the value of STACK cookie")
	public void stackCookies() {
		List<Cookie> allCookies = new ArrayList(driver.manage().getCookies());
		for(int i = 0; i < allCookies.size(); i++) {
			LOGGER.info("Cookie(" + i + ") value: " + allCookies.get(i).getValue() + "\n");
		}
	}
	
	@When("I click login button and log into the lobby with login: (.*) and password: (.*)")
	public void loginWeb(String login, String password) throws InterruptedException {
		driver.findElement(By.id("user")).sendKeys(login);
		driver.findElement(By.id("password")).sendKeys(password + "\n");
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	@When("I click over casino button")
	public void casinoClick() throws InterruptedException {
		driver.findElement(By.cssSelector("header > nav > ul > a:nth-child(4)")).click();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(3000);
	}
	
	@Then("I should see a list of games, count and list all games in A-Z")
	public void listGames() {
		List<WebElement> gameElement = driver.findElements(By.cssSelector(".gameName"));
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Collection<String> gameName = new TreeSet<String>(Collator.getInstance());
		if(gameElement.size() == 0) {
			fail("No games found");
		}
		for(int i=0; i < gameElement.size(); i++) {
			gameName.add(gameElement.get(i).getText());
		}
		LOGGER.info(gameName.toString());
	}
	
	
}
