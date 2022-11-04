import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestPlan {

    private static final WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void main() {
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test
    public void loginEmptyBothUsernameAndPassword() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.setEmptyUsername();
        loginForm.setEmptyPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String expected = "Atención: Debe ingresar un nombre de usuario";
        String result = loginForm.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void loginValidUsernameButEmptyPassword() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.setValidUsername();
        loginForm.setEmptyPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String expected = "Atención: El password no puede estar vacío";
        String result = loginForm.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void loginSuccessful() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.setValidUsername();
        loginForm.setValidPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String expected = "Bienvenido a OSTH On-Line";
        HomePage homePage = new HomePage(driver);
        String result = homePage.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void loginValidUsernameButWrongPassword() {
        driver.get(Utils.BASE_URL);
        LoginForm loginForm = new LoginForm(driver);

        loginForm.setValidUsername();
        loginForm.setWrongPassword();
        loginForm.pressLoginButton();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String expected = "Atención: El password ingresado no es válido";
        String result = loginForm.getMessage();

        assertEquals(expected, result);
    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}