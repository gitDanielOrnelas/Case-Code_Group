package br.ornelas.steps;

import br.ornelas.pages.MenuPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSearchSteps {
	
	private CommonSteps commonSteps = new CommonSteps();
	MenuPage menuPage = new MenuPage();

	public void chamandoGiven() throws InterruptedException {
		commonSteps.que_o_usuário_acessa_a_página_inicial();
	}

	@When("selecionar uma sugestão exibida")
	public void selecionar_uma_sugestão_exibida() {
		menuPage.selecionarPrimeiraSugestao();
	}

	@Then("o sistema deve exibir os resultados correspondentes à sugestão escolhida")
	public void o_sistema_deve_exibir_os_resultados_correspondentes_à_sugestão_escolhida() {
		menuPage.buscarProduto();
	    menuPage.validarTextoResultados();
	}

	@Then("o sistema não deve exibir nenhuma sugestão")
	public void o_sistema_não_deve_exibir_nenhuma_sugestão() {
	    menuPage.validarNenhumaSugestaoExibida();
	}
}