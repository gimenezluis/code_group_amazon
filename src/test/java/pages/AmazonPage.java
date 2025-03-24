package pages;

import org.openqa.selenium.By;

public class AmazonPage {

    // Campo de busca (topo da página)
    public static final By SEARCH_BOX = By.id("twotabsearchtextbox");

    // Sugestões exibidas no autocomplete
    public static final By AUTOCOMPLETE_ITEMS = By.xpath(".//div[@class='s-suggestion-container']");

    // Menu lateral (ícone de "hambúrguer")
    public static final By MENU_HAMBURGUER = By.id("nav-hamburger-menu");

    // Texto que exibe o termo buscado na página de resultados
    public static final By TEXTO_RESULTADO = By.xpath("//span[contains(@class, 'a-color-state')]");

    public static final By AUTOCOMPLETE_CONTAINER = By.xpath("//div[@class='left-pane-results-container']");

}
