package br.ornelas.steps;

import org.junit.Assert;

import br.ornelas.pages.MenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ResponsiveHomeMenuSteps {

	private CommonSteps commonSteps = new CommonSteps();
	MenuPage menuPage = new MenuPage();

	@Given("que o usuário acessa a página inicial no modo {string}")
	public void que_o_usuário_acessa_a_página_inicial_no_modo(String dispositivo) throws InterruptedException {
		commonSteps.que_o_usuário_acessa_a_página_inicial();
		menuPage.validaResponsividade(dispositivo);
	}

	@Then("menu {string} deverá estar visível")
	public void menu_deverá_estar_visível(String menu) {
		Assert.assertTrue("O menu " + menu + " não está visível!", menuPage.verificarMenuVisivel(menu));
	}
}
