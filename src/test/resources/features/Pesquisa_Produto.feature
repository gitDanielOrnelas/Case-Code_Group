#Author: Daniel Ornelas
#Version: 1
# Realizar o acesso ao site "https://www.amazon.com.br/"

@Pesquisa_produto
Feature: Realizar testes na barra de pesquisa da página inicial da Amazon
  
# O usuário digita "livro" e o sistema sugere itens relacionados, como "livro de ficção" ou "livro de romance". 
@TC001
  Scenario: Exibição de sugestões
    Given que o usuário acessa a página inicial
    When digitar "livro" no campo de pesquisa
    Then devemos validar que as sugestões estão de acordo com o inserido pelo usuário
	## Fim 1
	
# O usuário digita "@#%$$" e não recebe sugestões.
@TC002
  Scenario: Sugestão não exibida para termo inválido
    Given que o usuário acessa a página inicial
    When digitar "@#%$$" no campo de pesquisa
    Then o sistema não deve exibir nenhuma sugestão
	## Fim 2

	# O usuário clica em uma sugestão e o sistema executa a busca corretamente.
@TC003
  Scenario: Seleção de sugestão
    Given que o usuário acessa a página inicial
    When digitar "livro" no campo de pesquisa
    And selecionar uma sugestão exibida
    Then o sistema deve exibir os resultados correspondentes à sugestão escolhida
	## Fim 3