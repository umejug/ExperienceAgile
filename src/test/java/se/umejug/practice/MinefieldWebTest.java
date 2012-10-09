package se.umejug.practice;

import static org.fest.assertions.Assertions.assertThat;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class MinefieldWebTest {
	
	@Test
	public void shouldRevealCellWhenClicked() throws Exception {
		Server server = new Server(0);
		WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
		webAppContext.setAttribute("minefield", new Minefield(new String[] { ".*.", "..." }));
		server.setHandler(webAppContext);
		server.start();
		
		int localPort = server.getConnectors()[0].getLocalPort();
		String url = "http://localhost:" + localPort + "/";
		
		WebDriver browser = new HtmlUnitDriver() {
			@Override
			public WebElement findElement(By by) {
				try {
					return super.findElement(by);
				} catch (NoSuchElementException e) {
					throw new NoSuchElementException("Can't find " + by + " in " + getPageSource());
				}
			}
		};
		browser.get(url);

		assertThat(browser.findElement(By.xpath("//table//tr[1]/td[3]")).getText())
			.isEqualTo("?");
		browser.findElement(By.xpath("//table//tr[1]/tdt[3]//a")).click();
		
		assertThat(browser.findElement(By.xpath("//table//tr[1]/td[3]")).getText())
			.isEqualTo("1");
	}

}
