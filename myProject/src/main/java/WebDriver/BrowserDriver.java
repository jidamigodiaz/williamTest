package WebDriver;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class BrowserDriver {
	private static final Logger LOGGER = Logger.getLogger( BrowserDriver.class.getName() );
	private static WebDriver mDriver;
    
	public synchronized static WebDriver getCurrentDriver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\chromedriver.exe");
	    if (mDriver==null) {
	        try {
	        	    mDriver = new ChromeDriver();
	            } finally{
	                Runtime.getRuntime().addShutdownHook(
	                    new Thread(new BrowserCleanup()));
	            }
	    }
	    return mDriver;
	}
	 
	private static class BrowserCleanup implements Runnable {
	    public void run() {
	    	LOGGER.info("Closing the browser");
	        close();
	    }
	}
	 
	public static void close() {
	    try {
	        getCurrentDriver().quit();
	        mDriver = null;
	        LOGGER.info("closing the browser");
	    } catch (UnreachableBrowserException e) {
	        LOGGER.info("cannot close browser: unreachable browser");
	    }
	}
}
