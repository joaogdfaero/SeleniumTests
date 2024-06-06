// https://proleed.academy/exercises/selenium/online-payment-form-for-selenium-automation-practice.php
public class PagamentoConta{
    String url = 'https://sitedobanco.com.br'
    WebDriver driver
    
    @before
    public void iniciar(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().ImplicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @after
    public void finalizar(){
        if (driver != null){
            driver.quit()
        }
    }

    @test
    // When a user tries to pay an amount less than the account balance amount, he can pay the bill.
    // If a user tries to pay an amount more than the account balance, then the user should not be able to pay. 
    // A message should display not enough balance in the account.
    public void pagarconta(){
        driver.get(url);

        // Pega o valor da conta
        String balance = driver.findElement(By.id('accountBalance')).getAttribute("value");
        int balanceValue = Integer.parseInt(balance);

        if (balanceValue > 0){ 
            String positiveAlert = "Your payment is successful.";
            String negativeAlert = "You don't have enough balance in your account";

            // Adiciona no campo de pagamento um valor menor que o da conta
            driver.findElement(By.id('paymentAmount')).SendKeys(Integer.parseInt(balance) - 1);
            driver.findElement(By.id('payButton')).click();
            String result = driver.findElement(By.id('message')).getText();

            assertTrue(result.contains(positiveAlert), "The message should indicate a successful payment.");

            // Adiciona no campo de pagamento um valor igual ao da conta
            driver.findElement(By.id('paymentAmount')).SendKeys(Integer.parseInt(balance));
            driver.findElement(By.id('payButton')).click();
            String result = driver.findElement(By.id('message')).getText();

            assertTrue(result.contains(positiveAlert), "The message should indicate a successful payment.");

            // Adiciona no campo de pagamento um valor menor ao da conta
            driver.findElement(By.id('paymentAmount')).SendKeys(Integer.parseInt(balance) - 1);
            driver.findElement(By.id('payButton')).click();
            String result = driver.findElement(By.id('message')).getText();

            assertTrue(result.contains(negativeAlert), "The message should indicate a unsuccessful payment.");

        } else {
            // conta zerada
        }
    }
}