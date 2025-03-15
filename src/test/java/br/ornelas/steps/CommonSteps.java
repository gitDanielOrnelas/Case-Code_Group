package br.ornelas.steps;

import br.ornelas.pages.MenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

	MenuPage menuPage = new MenuPage();

	private String termoPesquisado; // Variável de instância para armazenar o termo digitado

	@Given("que o usuário acessa a página inicial") // OK
	public void que_o_usuário_acessa_a_página_inicial() throws InterruptedException {
		menuPage.acessarTelaInicial();
		// Aguarda 10 segundos antes de continuar para digitar captcha manualmente
		Thread.sleep(10000);
	}

	@When("digitar {string} no campo de pesquisa") // OK
	public void digitar_no_campo_de_pesquisa(String pesquisa) {
		this.termoPesquisado = pesquisa;
		menuPage.digitarPesquisa(pesquisa);
	}

	@Then("devemos validar que as sugestões estão de acordo com o inserido pelo usuário") // OK
	public void devemos_validar_que_as_sugestões_estão_de_acordo_com_o_inserido_pelo_usuário() {
	    menuPage.validarSugestoesComTermo(termoPesquisado);
	}
}
