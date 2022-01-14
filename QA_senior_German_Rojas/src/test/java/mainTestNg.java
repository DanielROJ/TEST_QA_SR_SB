import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class mainTestNg {


    WebDriver webDriver;
    Map mapElements = new HashMap<String,String>();
    By input_email_login_L = By.id("username");
    By input_pass_login_L = By.id("password");
    By submit_login_L = By.name("login");
    By label_dashboard_L  = By.xpath("//a[contains(@href,'http://practice.automationtesting.in/my-account/') and contains(.,'Dashboard')]");

    @DataProvider(name = "data")
    public Object[][] data(){
        return new Object[][]{
                new Object[]{"recinnamuj-6813@yopmail.com","4187260.pacheco"}
        };
    }

    /**
     * Metodo que permite preparar precondiciones del entorno necesarias para realizar el test automatizado"
     */
    @BeforeClass
    public void BeoforeClass(){
        ChromeOptions options=new ChromeOptions();
        this.webDriver = new ChromeDriver(options);
        this.webDriver.manage().window().maximize();
        this.webDriver.get("http://practice.automationtesting.in/my-account/");

    }

    /**
     * Metodo que ejecuta y orquesta los pasos a seguir por la utomatización, ademas de verificar el resultado esperado.
     */
    @Test(dataProvider = "data")
    public void mainTest(String email, String password){
        try {
            WebDriverWait wait =  new WebDriverWait(webDriver, 20);

            wait.until(ExpectedConditions.presenceOfElementLocated(input_email_login_L));

            WebElement input_email_login = this.webDriver.findElement(input_email_login_L);
            input_email_login.clear();
            input_email_login.sendKeys(email);

            WebElement input_pass_login = this.webDriver.findElement(input_pass_login_L);
            input_pass_login.clear();
            input_pass_login.sendKeys(password);

            WebElement submit_login = this.webDriver.findElement(submit_login_L);
            submit_login.click();
            /*
            JavascriptExecutor executor = (JavascriptExecutor) driverFacade.getWebDriver();
            executor.executeScript("arguments[0].click();", element);
*/
            wait.until(ExpectedConditions.presenceOfElementLocated(label_dashboard_L));
            WebElement label_dashboard = this.webDriver.findElement(label_dashboard_L);
            Assert.assertEquals("Dashboard", label_dashboard.getText());

        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Metodo encargado de cerrar detalles o realizar pasos depues de la ejecución de los test
     */
    @AfterClass
    public void afterClass(){
        this.webDriver.close();
        this.webDriver.quit();
    }

}
