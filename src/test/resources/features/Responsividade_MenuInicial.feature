#Author: Daniel Ornelas
#Version: 1
# Realizar o acesso ao site "https://www.amazon.com.br/",

@Responsividade_MenuInicial
Feature: Realizar testes de responsividade do Menu da inicial da Amazon
  
# Menu suspenso funcionando corretamente 
@TC005
	Scenario: Menu suspenso funcionando corretamente
    Given que o usuário acessa a página inicial
		When digitar "livro" no campo de pesquisa
    Then devemos validar que as sugestões estão de acordo com o inserido pelo usuário
	## Fim 1
	
# Menu funciona no desktop e "Venda na Amazon" está visível
@TC006 @TC007
	Scenario Outline: Menu funciona em tamanhos diferentes de responsividade
    Given que o usuário acessa a página inicial no modo <dispositivo>
    When a página é carregada completamente
    Then menu "Venda na Amazon" deverá estar visível

  Examples:
      | dispositivo |
      | "desktop"     |
      | "mobile"      |
      
	## Fim 2