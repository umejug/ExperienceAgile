package se.umejug.practice;


import static org.fest.assertions.Assertions.assertThat;

import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class MinefieldWebTest {
	
	@Test
	public void shouldRevealHintsOnClick() throws Exception {
		Server server = new Server(0);
		WebAppContext webAppContext = new WebAppContext("src/main/webapp", "/");
		server.setHandler(webAppContext);
		server.start();
		
		Servlet servlet = webAppContext.getServletContext().getServlet("minefieldServlet");
		((MinefieldServlet)servlet).setMinefield(new Minefield(new String[] {"....", ".*..", ".*.*", "...*"}));
		
		String appUrl = "http://localhost:" + server.getConnectors()[0].getLocalPort() + "/";
		
		WebDriver browser = new HtmlUnitDriver();
		browser.get(appUrl);
		
		assertThat(browser.findElement(By.xpath("//table//tr[1]/tr[3]")).getText()).isEqualTo("?");
		browser.findElement(By.xpath("//table//tr[1]/tr[3]")).click();
		
		assertThat(browser.findElement(By.xpath("//table//tr[1]/tr[3]")).getText()).isEqualTo("1");
	}

}
