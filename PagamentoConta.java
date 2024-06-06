// https://proleed.academy/exercises/selenium/online-payment-form-for-selenium-automation-practice.php
public class PagamentoConta {
    String url = 'https://sitedobanco.com.br';
    WebDriver driver;
    
    @Before
    public void iniciar() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void finalizar() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void pagarconta() {
        driver.get(url);

        // Pega o valor da conta
        String balance = driver.findElement(By.id("accountBalance")).getAttribute("value");
        int balanceValue = Integer.parseInt(balance);

        if (balanceValue > 0) { 
            // Teste com valor menor que o saldo
            realizarPagamento(balanceValue, balanceValue - 1);

            // Teste com valor igual ao saldo
            realizarPagamento(balanceValue, balanceValue);

            // Teste com valor maior que o saldo
            realizarPagamento(balanceValue, balanceValue + 1);
        } else {
            // Tratamento para caso o saldo seja zero
            System.out.println("O saldo da conta é zero, não é possível realizar testes de pagamento.");
        }
    }

    private void realizarPagamento(int saldoConta, int valorPagamento) {
        String positiveAlert = "Your payment is successful.";
        String negativeAlert = "You don't have enough balance in your account";
        String mensagemEsperada = '';

        driver.findElement(By.id("paymentAmount")).clear();
        driver.findElement(By.id("paymentAmount")).sendKeys(String.valueOf(valorPagamento));
        driver.findElement(By.id("payButton")).click();
        String resultado = driver.findElement(By.id("message")).getText();

        if (saldoConta >= valorPagamento){
            mensagemEsperada = positiveAlert 
        } else {
            mensagemEsperada = negativeAlert
        }

        assertTrue(resultado.contains(mensagemEsperada), "A mensagem deve indicar: " + mensagemEsperada);
    }
}
