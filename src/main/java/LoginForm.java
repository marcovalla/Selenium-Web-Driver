import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginForm extends PageObject{

    private final String USERNAME = "dumbridge";
    private final String PASSWORD = "tomriddle";
    private final String WRONG_PASSWORD = "wrongpassword";

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//button")
    private WebElement login_button;

    @FindBy(id = "estado")
    private WebElement message;

    public LoginForm(WebDriver driver) {
        super(driver);
    }

    public void setEmptyUsername(){
        this.username.sendKeys("");
    }

    public void setEmptyPassword(){
        this.password.sendKeys("");
    }

    public void setValidUsername(){
        this.username.sendKeys(USERNAME);
    }

    public void setValidPassword(){
        this.password.sendKeys(PASSWORD);
    }

    public void setWrongPassword(){
        this.password.sendKeys(WRONG_PASSWORD);
    }

    public void pressLoginButton(){
        this.login_button.click();
    }

    public String getMessage() {
        return message.getText();
    }
}
