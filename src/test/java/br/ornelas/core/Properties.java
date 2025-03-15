package br.ornelas.core;

public class Properties {

	// false = ótimo para desenvolver os testes
	// true = utilizado quando subir na esteira, pois um teste não deve depender de outro,
	// e poderá haver sujeira no browser do teste anterior
	public static boolean FECHAR_BROWSER = false;

	//definindo propriedade (padrão será Chrome)
	//public static Browsers browser = Browsers.CHROME;
	//public static Browsers browser = Browsers.FIREFOX;
	
	// Chaveamento de browsers nos testes
	public enum Browsers {
		CHROME,
		FIREFOX
	}
}
