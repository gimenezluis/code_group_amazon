@AmazonTeste
Feature: Autocomplete e menu de navegação

  @Valido
  Scenario: Exibir sugestões ao digitar na barra de pesquisa
    Given Abro a pagina inicial da Amazon
    When Digito "playstation" no campo de busca e valido clicando no autocomplete "playstation 5"
    Then Vejo sugestoes relacionadas com a busca "playstation"

  @Valida_Nulo
  Scenario: Nenhuma sugestão ao digitar palavra inválida
    Given Abro a pagina inicial da Amazon
    When Digito "@#¨&¨%#@" no campo de busca
    Then Nenhuma sugestao deve aparecer

  @Responsivo
  Scenario Outline: Menu funciona corretamente em diferentes tamanhos de tela
    Given Eu defino a resolucao "<resolucao>"
    When Abro a pagina inicial da Amazon
    Then O menu de navegacao deve estar acessivel

    Examples:
      | resolucao |
      | desktop   |
      | tablet    |
      | mobile    |

