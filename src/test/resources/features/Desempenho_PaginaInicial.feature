#Author: Daniel Ornelas
#Version: 1
# Realizar o acesso ao site "https://www.amazon.com.br/",

@Desempenho_PaginaInicial
Feature: Validar tempo de carregamento da página inicial da Amazon
  
# O usuário acesse a amazon pelo navegador Chrome 
  @TC010
  Scenario: Carregamento rápido Chrome
    Given que o usuário acessa a página inicial com o navegador "Chrome"
    When a página é carregada completamente
    Then devemos validar que o carregamento foi menor que 1 segundos
	## Fim 1
	
# O usuário acesse a amazon pelo navegador Firefox
	@TC011
  Scenario: Carregamento rápido Firefox
    Given que o usuário acessa a página inicial com o navegador "Firefox"
		When a página é carregada completamente
    Then devemos validar que o carregamento foi menor que 1 segundos
	## Fim 2
	
# O usuário acesse a página inicial da amazon com tempo superir a 5s
	@TC012
  Scenario: Tempo de carregamento alto
    Given que o usuário acessa a página inicial
    When a página é carregada completamente
    Then devemos validar que o carregamento não poderá ser maior que 5 segundos
	## Fim 3
