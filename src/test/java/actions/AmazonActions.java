package actions;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.AmazonPage;
import utils.DriverUtil;
import utils.WaitUtil;

public class AmazonActions {

    WebDriver driver = DriverUtil.getDriver();

    public void abrirPaginaInicial() {
        driver.get("https://www.amazon.com.br/");
    }

    public void digitarNoCampoDeBusca(String termo) {
        try {
            WebElement searchBox = WaitUtil.waitForVisibility(AmazonPage.SEARCH_BOX);
            searchBox.clear();
            searchBox.sendKeys(termo);
        } catch (Exception e) {
            System.out.println("Campo de busca não encontrado. Recarregando a página...");
            driver.navigate().refresh();

            try {
                WebElement searchBox = WaitUtil.waitForVisibility(AmazonPage.SEARCH_BOX);
                searchBox.clear();
                searchBox.sendKeys(termo);
            } catch (Exception ex) {
                throw new RuntimeException("Mesmo após recarregar, o campo de busca não foi encontrado.");
            }
        }
    }

    public void validarSugestoesAparecem() {
        List<WebElement> sugestoes = driver.findElements(AmazonPage.AUTOCOMPLETE_ITEMS);
        assertTrue("Nenhuma sugestão foi exibida!", sugestoes.size() > 0);
    }

    public void validarNenhumaSugestao() {
        List<WebElement> sugestoes = driver.findElements(AmazonPage.AUTOCOMPLETE_ITEMS);
        assertFalse("Sugestões foram exibidas, mas não deveriam.", sugestoes.size() > 0);
    }

    public void selecionarSugestaoNoAutocomplete(String textoDesejado) {
        try {
            WaitUtil.waitForVisibility(AmazonPage.AUTOCOMPLETE_CONTAINER);
            WaitUtil.waitUntilListHasItems(AmazonPage.AUTOCOMPLETE_ITEMS);

            boolean encontrado = false;

            int tentativas = 0;
            while (tentativas < 3 && !encontrado) {
                List<WebElement> itensAtualizados = DriverUtil.getDriver().findElements(AmazonPage.AUTOCOMPLETE_ITEMS);

                for (WebElement item : itensAtualizados) {
                    try {
                        String nome = item.getText();
                        System.out.println("Produto: " + nome);
                        if (nome.equalsIgnoreCase(textoDesejado)) {
                            item.click();
                            encontrado = true;
                            break;
                        }
                    } catch (org.openqa.selenium.StaleElementReferenceException e) {
                        System.out.println("Elemento ficou stale, tentando novamente...");
                        break; // quebra o for e recarrega a lista
                    }
                }
                tentativas++;
            }

            if (!encontrado) {
                fail("Sugestão '" + textoDesejado + "' não foi encontrada ou não pôde ser clicada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            fail("Erro ao selecionar sugestão: " + e.getMessage());
        }
    }

    public void validarTextoResultadoNaPaginaDeBusca(String termoEsperado) {
        WebElement resultado = WaitUtil.waitForVisibility(AmazonPage.TEXTO_RESULTADO);

        String texto = resultado.getText().toLowerCase();
        assertTrue("Resultado da busca não contém o termo esperado.",
                texto.contains(termoEsperado.toLowerCase()));
    }

    public void definirResolucao(String resolucao) {
        switch (resolucao.toLowerCase()) {
            case "desktop":
                DriverUtil.setViewportDesktop();
                break;
            case "tablet":
                DriverUtil.setViewportTablet();
                break;
            case "mobile":
                DriverUtil.setViewportMobile();
                break;
            default:
                throw new IllegalArgumentException("Resolução inválida: " + resolucao);
        }
    }

    public void validarMenuEstaVisivel() {
        WebElement menu = WaitUtil.waitForVisibility(AmazonPage.MENU_HAMBURGUER);
        assertTrue("Menu de navegação não está visível!", menu.isDisplayed());
    }

    public void validarTempoDeCarregamentoInferiorA(int limiteMilissegundos) {
        Double duracao = (Double) ((JavascriptExecutor) driver)
                .executeScript("return performance.getEntriesByType('navigation')[0].duration;");

        System.out.println("Tempo de carregamento: " + duracao.longValue() + " ms");
        assertTrue("Tempo excedeu o limite: " + duracao.longValue() + " ms", duracao < limiteMilissegundos);
    }

}
