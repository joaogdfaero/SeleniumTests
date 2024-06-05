//Exercise 1:
//Write a script to open google.com and verify that title is Google and also verify that it is redirected to https://www.google.com.br/
//Exercise 2:
//Abre o GitHub, se não tiver logado faz login, clica no "Create New" e clica em "New repository"

public class Exercises {

    @Before
    public void iniciar(){
        driver = new ChromeDriver();
    }

    @After
    public void finalizar(){
        driver.quit();
    }

    @Test
    public void exercise1(){
        driver.get('www.google.com');

        String title = driver.getTitle();
        String url = driver.getCurrentUrl();

        AssertEquals('Google', title);
        AssertTrue(driver.getCurrentUrl().equals("www.google.com.br"));
    }

    @Test
    public void exercise2() {
    WebDriver driver = new ChromeDriver(); // Inicialização do WebDriver
    driver.get("https://www.github.com");

    WebElement buttonContent = null;
    try {
        buttonContent = driver.findElement(By.cssSelector("span.Button-content"));
    } catch (NoSuchElementException e) {
        // Faça algo caso o elemento não seja encontrado, como imprimir uma mensagem de erro ou lançar uma exceção
    }

    if (buttonContent != null) {
        buttonContent.click(); // "click()" com "c" minúsculo

        WebElement buttonNewRepo = null;
        try {
            buttonNewRepo = driver.findElement(By.xpath("//*[@id=\":r11:--label\"]"));
        } catch (NoSuchElementException e) {
            // Faça algo caso o elemento não seja encontrado, como imprimir uma mensagem de erro ou lançar uma exceção
        }

        if (buttonNewRepo != null) {
            buttonNewRepo.click(); // "click()" com "c" minúsculo
        }
    } else {
        fazerLogin();
        exercise2(); // Chama a função novamente após fazer login
    }
}

// Função para fazer login
public void fazerLogin() {
    // Implemente o código para fazer login aqui
}

}
