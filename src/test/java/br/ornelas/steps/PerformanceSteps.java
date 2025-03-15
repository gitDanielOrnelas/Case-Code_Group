package br.ornelas.steps;

import br.ornelas.core.DriverFactory;
import br.ornelas.pages.MenuPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PerformanceSteps {

	private long startTime;
	private long endTime;
	private CommonSteps commonSteps = new CommonSteps();
	MenuPage menuPage = new MenuPage();

	@Given("que o usuário acessa a página inicial com o navegador {string}")
	public void que_o_usuário_acessa_a_página_inicial_com_o_navegador(String navegador) throws InterruptedException {
		DriverFactory.setBrowser(navegador);
		commonSteps.que_o_usuário_acessa_a_página_inicial();
		startTime = System.currentTimeMillis();
	}

	@When("a página é carregada completamente")
	public void a_página_é_carregada_completamente() {
		endTime = System.currentTimeMillis();
	}

	@Then("devemos validar que o carregamento foi menor que {int} segundos")
	public void devemos_validar_que_o_carregamento_foi_menor_que_segundos(int tempo) {
		menuPage.validarTempoDeCarregamento(startTime, endTime, tempo);
	}

	@Then("devemos validar que o carregamento não poderá ser maior que {int} segundos")
	public void devemos_validar_que_o_carregamento_não_poderá_ser_maior_que_segundos(int tempo) {
		MenuPage menuPage = new MenuPage();
		menuPage.validarTempoMaximoDeCarregamento(startTime, endTime, tempo);
	}

}
