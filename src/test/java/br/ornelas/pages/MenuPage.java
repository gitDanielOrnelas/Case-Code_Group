package br.ornelas.pages;

import static br.ornelas.core.DriverFactory.getDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ornelas.core.DSL;

public class MenuPage extends DSL {

	public void acessarTelaInicial() {
		WebDriver driver = getDriver();
		driver.get("https://www.amazon.com.br/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// CAPTCHA: Fique atento ao possível captcha da Amazon, coloquei um contorno na
		// aplicação,
		// onde descobri que clicando no link "Tentar uma imagem diferente", ele avança
		// pra tela inicial.
		// Não sei dizer se foi proposital da Amazon para aplicações automáticas, ou é
		// se um BUG.
		try {
			// Verifica se o link "Tentar uma imagem diferente" está presente
			WebElement linkCaptcha = wait.until(ExpectedConditions
					.presenceOfElementLocated(By.xpath("//a[contains(text(), 'Tentar uma imagem diferente')]")));

			// Se o link do captcha for encontrado, ele clica no link para passar
			if (linkCaptcha.isDisplayed()) {
				linkCaptcha.click();
				// driver.navigate().refresh();
			}
		} catch (TimeoutException e) {
			// Se o captcha não apareceu, segue normalmente
			System.out.println("Captcha não detectado, seguindo para a página inicial.");
		}
	}

	// Método para digitar no campo de pesquisa
	public void digitarPesquisa(String pesquisa) {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
		// Espera até que o elemento seja clicável
		WebElement campoPesquisa = wait.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));

		campoPesquisa.sendKeys(pesquisa);
	}

	// Método para apenas 1 item da lista com "aria-label"
	public String salvarSugestoesTexto() {
		try {
			Thread.sleep(3000);

			WebElement sugestaoElemento = getDriver().findElement(By.xpath(
					"//div[contains(@class, 's-suggestion') and contains(@class, 's-suggestion-ellipsis-direction')]")); // funcionando

			// Captura o valor do atributo "aria-label"
			String ariaLabel = sugestaoElemento.getAttribute("aria-label");

			return ariaLabel;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}

	}

	// Método para capturar 10 ou menos itens da lista com "aria-label"
	public List<String> salvarSugestoesTextoLista() {
		List<String> sugestoes = new ArrayList<>();

		try {
			// Espera de 3 segundos
			Thread.sleep(3000);

			// Localiza todos os elementos que correspondem ao critério
			List<WebElement> sugestaoElementos = getDriver().findElements(By.xpath(
					"//div[contains(@class, 's-suggestion') and contains(@class, 's-suggestion-ellipsis-direction')]"));

			// Limita a captura a no máximo 10 sugestões
			int limite = Math.min(sugestaoElementos.size(), 10);

			// Itera sobre os elementos encontrados e armazena o atributo "aria-label"
			for (int i = 0; i < limite; i++) {
				String ariaLabel = sugestaoElementos.get(i).getAttribute("aria-label");
				sugestoes.add(ariaLabel); // Adiciona o valor à lista
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return sugestoes; // Retorna a lista de sugestões
	}

	public void validarNenhumaSugestaoExibida() {
		List<String> sugestoes = salvarSugestoesTextoLista();

		// Valida se a lista de sugestões está vazia
		Assert.assertTrue("Sugestões foram exibidas para termo inválido!", sugestoes.isEmpty());
	}

	// método buscarProduto já digitado
	public void buscarProduto() {
		WebElement botaoSubmit = getDriver().findElement(By.id("nav-search-submit-button"));
		botaoSubmit.click();
	}

	// Método para validar o texto "Resultados" após pesquisa
	public void validarTextoResultados() {
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		WebElement elementoResultados = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("a-size-medium-plus")));

		String textoResultados = elementoResultados.getText();

		Assert.assertEquals("O texto 'Resultados' não foi encontrado!", "Resultados", textoResultados);
	}

	public void selecionarPrimeiraSugestao() {
		List<WebElement> sugestoes = getDriver().findElements(By.xpath("//div[@class='s-suggestion']"));
		if (!sugestoes.isEmpty()) {
			sugestoes.get(0).click();
		} else {
			System.out.println("Nenhuma sugestão encontrada.");
		}
	}

	// Método para validar se as sugestões contêm o termo pesquisado
	public void validarSugestoesComTermo(String termoPesquisado) {
		List<String> sugestoes = salvarSugestoesTextoLista();

		// Verifica se pelo menos uma sugestão contém o termo pesquisado
		boolean sugestaoValida = sugestoes.stream().anyMatch(s -> s.contains(termoPesquisado));

		// Imprime resultado da validação
		System.out.println("Sugestões Obtidas: " + sugestoes);
		System.out.println("Termo Pesquisado: " + termoPesquisado);

		// Valida se encontrou pelo menos uma sugestão válida
		Assert.assertTrue("Nenhuma sugestão corresponde ao texto inserido: " + termoPesquisado, sugestaoValida);
	}

	// RESPOSIVIDADE

	public void validaResponsividade(String dispositivo) {
		// Ajusta a resolução da tela
		if (dispositivo.equalsIgnoreCase("desktop")) {
			getDriver().manage().window().maximize(); // Simula Desktop
		} else if (dispositivo.equalsIgnoreCase("mobile")) {
			getDriver().manage().window().setSize(new Dimension(375, 667)); // Simula Mobile
		}
	}

	// Método para verificar se o menu está visível
	public boolean verificarMenuVisivel(String menu) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(menu)));

			return elemento.isDisplayed();
		} catch (NoSuchElementException e) {
			return false; // Caso o elemento não seja encontrado
		}
	}

	// DESEMPENHO

	public void validarTempoDeCarregamento(long startTime, long endTime, int tempoLimiteSegundos) {
		long tempoCarregamento = endTime - startTime; // tempo em milissegundos
		long tempoLimite = tempoLimiteSegundos * 1000; // Convertendo o tempo para milissegundos

		System.out.println("Tempo esperado: < " + tempoLimiteSegundos + "s");
		System.out.println("Tempo de carregamento: " + tempoCarregamento + "ms");

		// Verificando se o tempo de carregamento é menor que o limite
		Assert.assertTrue("Erro: Tempo de carregamento maior que " + tempoLimiteSegundos + " segundos!",
				tempoCarregamento <= tempoLimite);
	}

	public void validarTempoMaximoDeCarregamento(long startTime, long endTime, int tempoLimiteSegundos) {
		long tempoCarregamento = endTime - startTime; // Tempo em milissegundos
		long tempoLimite = tempoLimiteSegundos * 1000; // Convertendo segundos para milissegundos

		System.out.println("Tempo esperado: <= " + tempoLimiteSegundos + "s");
		System.out.println("Tempo de carregamento: " + tempoCarregamento + "ms");

		// Valida se o tempo de carregamento está dentro do limite
		Assert.assertTrue("Erro: O tempo de carregamento foi de " + tempoCarregamento + "ms, mas o limite era "
				+ tempoLimite + "ms!", tempoCarregamento <= tempoLimite);
	}

}
