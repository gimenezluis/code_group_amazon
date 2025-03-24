package steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.JavascriptExecutor;

import actions.AmazonActions;
import io.cucumber.java.en.*;

public class AmazonSteps {

    AmazonActions actions = new AmazonActions();

    @Given("Abro a pagina inicial da Amazon")
    public void abro_a_pagina_inicial_da_amazon() {
        actions.abrirPaginaInicial();
    }

    @When("Digito {string} no campo de busca e valido clicando no autocomplete {string}")
    public void digito_no_campo_de_busca_e_valido_autocomplete(String termo, String sugestao) {
        actions.digitarNoCampoDeBusca(termo);
        actions.selecionarSugestaoNoAutocomplete(sugestao);
    }

    @Then("Vejo sugestoes relacionadas com a busca {string}")
    public void vejo_sugestoes_relacionadas_com_a_busca(String termoEsperado) {
        actions.validarTextoResultadoNaPaginaDeBusca(termoEsperado);
    }

    @When("Digito {string} no campo de busca")
    public void digito_no_campo_de_busca(String termo) {
        actions.digitarNoCampoDeBusca(termo);
    }

    @Then("Nenhuma sugestao deve aparecer")
    public void nenhuma_sugestao_deve_aparecer() {
        actions.validarNenhumaSugestao();
    }

    @Given("Eu defino a resolucao {string}")
    public void eu_defino_a_resolucao(String resolucao) {
        actions.definirResolucao(resolucao);
    }

    @Then("O menu de navegacao deve estar acessivel")
    public void o_menu_de_navegacao_deve_estar_acessivel() {
        actions.validarMenuEstaVisivel();
    }

    long inicioCarregamento;

    @Then("O tempo de carregamento deve ser inferior a {int} milissegundos")
    public void o_tempo_de_carregamento_deve_ser_inferior_a(Integer limite) {
        actions.validarTempoDeCarregamentoInferiorA(limite);
    }

}
