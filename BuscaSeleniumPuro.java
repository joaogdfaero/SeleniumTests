public class BuscaSeleniumPuro{
    String url = 'https://iterasys.com.br/';
    WebDriver driver;

    @Before
    public void iniciar(){
        driver = new ChromeDriver();
        // Timeout implícito
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILISECONDS)
        // Evita que tenha outra tela na frente
        driver.manage().window().maximize()
    }

    @After
    public void finalizar(){
        driver.quit()
    }

    @Test
    public void buscacurso(){
        driver.get(url) // acessa o URL
        driver.findElement(By.id('searchtext')).sendKeys('TestLink' + Keys.ENTER); // Acha o campo de busca, digita a busca e aperta enter
        driver.findElement(By.cssSelector('span.comprar')).click(); // acha o botão compra e realiza a compra
    }  
}
