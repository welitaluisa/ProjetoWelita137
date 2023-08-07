package webTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BKCamisasTest {

    private WebDriver driver;
    WebDriverWait wait;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        //js = (JavascriptExecutor) driver;
        //vars = new HashMap<String, Object>();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofMillis(9000));
    }

    @AfterEach
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void testComprarCamisa() {
        // entra no site
        driver.get("https://www.bkcamisas.com/");
        // clicar no link busca
        driver.findElement(By.id("input_search-box-input-comp-kmnx7efj")).click();

        //Pesquisa pela "Jaqueta Seleção Argentina 2019 (Nova)"
        driver.findElement(By.id("input_search-box-input-comp-kmnx7efj")).sendKeys("Jaqueta Seleção Argentina 2019 (Nova)");
        driver.findElement(By.id("input_search-box-input-comp-kmnx7efj")).sendKeys(Keys.ENTER);

        // Aguardar a jaqueta aparecer na lista
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[data-hook='item-title'].sil_d4M")));

        //clica no botão Adicionar ao carrinho

      driver.findElement(By.xpath("//*[@id=\"TPASection_kmnx7dok\"]/div/div/div/div/div/div[2]/div[2]/div[2]/ul/li[1]/div/a")).click();


        // validar o nome da jaqueta

        assertEquals("Jaqueta Seleção Argentina 2019 (Nova)",driver.findElement(By.cssSelector("#i40xifqz > div > div > article > div._12vNY > section:nth-child(2) > div:nth-child(1) > h1")).getText());


        // valida o preco da jaqueta

       assertEquals("R$ 299,90", driver.findElement(By.cssSelector("#i40xifqz > div > div > article > div._12vNY > section:nth-child(2) > div._3bNb3.fggS-.cell > div > div > div > span:nth-child(1)")).getText());

        // Selecionar o tamanho da jaqueta " M"


        driver.findElement(By.cssSelector("#label-for-id_-336 > span.CoreButtonNext960945004__content")).click();

     //   { // Inicio da seleção dentro da lista
      //      WebElement lista = driver.findElement(By.className("DropdownNativeSelect876435390__root DropdownNativeSelect876435390--placeholder DropdownNativeSelect876435390--fullWidth Dropdown3680886578__dropdownNativeSelect"));
      //      lista.findElement(By.xpath("//option[@value='M']")).click();

     //   } // Fim da seleção dentro da lista

   }
}
