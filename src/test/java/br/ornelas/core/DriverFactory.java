package br.ornelas.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;
	private static String navegadorAtual = "CHROME";

	public DriverFactory() {
	}

	// definir o navegador antes de obter o driver
	public static void setBrowser(String navegador) {
		navegadorAtual = navegador.toUpperCase();
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (navegadorAtual.toUpperCase()) {

			case "FIREFOX":
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\User\\OneDrive\\Documentos\\Automação\\firefox\\geckodriver.exe");

				// FirefoxOptions firefoxOptions = new FirefoxOptions();
				// firefoxOptions.addArguments("--headless"); // Executar em modo headless (sem abrir o navegador)

				driver = new FirefoxDriver();
				break;
			case "CHROME":
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\User\\OneDrive\\Documentos\\Automação\\chromedriver-win64 - v134\\chromedriver.exe");

				// ChromeOptions chromeOptions = new ChromeOptions();
				// chromeOptions.addArguments("--headless"); // Executar em modo headless (sem abrir o navegador)

				driver = new ChromeDriver();
				break;

			}
			driver.manage().window().setSize(new Dimension(1200, 765));

		}
		return driver;
	}

	public static void killDriver() {
		if (driver != null) {
			System.out.println("Finalizando o driver...");
			driver.quit();
			driver = null;
			navegadorAtual = "CHROME"; // Resetando o navegador para o padrão
		} else {
			System.out.println("Driver já estava nulo.");
		}

	}
}
