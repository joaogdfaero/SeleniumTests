//Exercise 1:
//Write a script to open google.com and verify that title is Google and also verify that it is redirected to https://www.google.com.br/

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
}
