package br.ornelas.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun  = false, //true = roda em segundo plano
		monochrome = true,
		//features = "src/test/resources/features/Pesquisa_Produto.feature",
		//features = "src/test/resources/features/Responsividade_MenuInicial.feature",
		features = "src/test/resources/features/Desempenho_PaginaInicial.feature",
		//features = "src/test/resources/features/",
		glue = {"br.ornelas.steps","br.ornelas.utilities"},
		//tags = "@TC012",
		plugin = {
				"pretty",
				"html:target/cucumber-reports.html", "json:target/cucumber.json" 				
		}
)

public class RunCucumberTest {

}
