package trello;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EndToEnd {
	WebDriver driver;

	@Parameters({ "email","password"})
	@Test(priority = 1)
	public void LoginToTrelloApplication(String email, String password) {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get("https://trello.com/home");
		WebElement homePageLoginButton = driver.findElement(By.xpath("//div[@class='Buttonsstyles__ButtonGroup-sc-1jwidxo-3 jnMZCI']/a[text()='Log in']"));
		homePageLoginButton.click();
		WebElement emailTextField = driver.findElement(By.id("username"));
		emailTextField.sendKeys(email);
		WebElement continueOption = driver.findElement(By.xpath("//span[text()='Continue']"));
		continueOption.click();
		wait.until(ExpectedConditions.titleIs("Log in to continue - Log in with Atlassian account"));
		WebElement passwordTextField = driver.findElement(By.id("password"));
		passwordTextField.sendKeys(password);
		WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']"));
		loginButton.click();
	}
	@Parameters({"input"})
	@Test(priority = 2, dependsOnMethods = "LoginToTrelloApplication")
	public void CreatingBoard(String input) {
		WebElement createOption = driver.findElement(By.xpath("//p[text()='Create']"));
		createOption.click();
		WebElement createBoardOption = driver.findElement(By.xpath("//span[text()='Create board']"));
		createBoardOption.click();
		WebElement inputTextField = driver.findElement(By.xpath("//input[contains(@class,'nch-textfield__input ')]"));
		inputTextField.sendKeys(input);
		WebElement createBoardDrop = driver.findElement(By.xpath("//button[text()='Create']"));
		createBoardDrop.click();
	}
	@Parameters({"one","two","three","four"})
	@Test(priority = 3, dependsOnMethods = "CreatingBoard")
	public void AddingLists(String one, String two, String three, String four) {
		WebElement listItemOne = driver.findElement(By.xpath("//textarea[@class='oe8RymzptORQ7h']"));
		listItemOne.sendKeys(one);
		WebElement addOptionOne = driver.findElement(By.xpath("//button[text()='Add list']"));
		addOptionOne.click();
		WebElement listItemTwo = driver.findElement(By.xpath("//textarea[@class='oe8RymzptORQ7h']"));
		listItemTwo.sendKeys(two);
		WebElement addOptionTwo = driver.findElement(By.xpath("//button[text()='Add list']"));
		addOptionTwo.click();
		WebElement listItemThree = driver.findElement(By.xpath("//textarea[@class='oe8RymzptORQ7h']"));
		listItemThree.sendKeys(three);
		WebElement addOptionThree = driver.findElement(By.xpath("//button[text()='Add list']"));
		addOptionThree.click();
		WebElement listItemFour = driver.findElement(By.xpath("//textarea[@class='oe8RymzptORQ7h']"));
		listItemFour.sendKeys(four);
		WebElement addOptionFour = driver.findElement(By.xpath("//button[text()='Add list']"));
		addOptionFour.click();
	}
	@Parameters({"testNG","Joins","oops","sdlc"})
	@Test(priority = 4, dependsOnMethods = "AddingLists")
	public void AddingToCart(String testNG, String Joins, String oops, String sdlc) {
		WebElement addToCartbutton = driver.findElement(By.xpath("//button[contains(@class,'O9vivwyDxMqo3q bxgKMAm3lq5BpA iUcMbl')]"));
		addToCartbutton.click();
		WebElement textAreaOne = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		textAreaOne.sendKeys(testNG);
		WebElement addCartOne = driver.findElement(By.xpath("//button[text()='Add card']"));
		addCartOne.click();
		WebElement textAreaTwo = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		textAreaTwo.sendKeys(Joins);
		WebElement addCartTwo = driver.findElement(By.xpath("//button[text()='Add card']"));
		addCartTwo.click();
		WebElement textAreaThree = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		textAreaThree.sendKeys(oops);
		WebElement addCartThree = driver.findElement(By.xpath("//button[text()='Add card']"));
		addCartThree.click();
		WebElement textAreaFour = driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']"));
		textAreaFour.sendKeys(sdlc);
		WebElement addCartFour = driver.findElement(By.xpath("//button[text()='Add card']"));
		addCartFour.click();	
	}
	@Test(priority = 5, dependsOnMethods = "AddingToCart")
	public void DragAndDrop() {
		WebElement testngDrag = driver.findElement(By.xpath("(//div[@class='amUfYqLTZOvGsn'])[1]"));
		WebElement testngDrop = driver.findElement(By.xpath("(//div[@class='S10qr1dFzMP0OB'])[4]"));
//		WebElement joinsDrag = driver.findElement(By.xpath("(//div[@class='amUfYqLTZOvGsn'])[3]"));
//		WebElement joinsDrop = driver.findElement(By.xpath("(//div[@class='S10qr1dFzMP0OB'])[2]"));
		WebElement oopsDrag = driver.findElement(By.xpath("(//div[@class='amUfYqLTZOvGsn'])[3]"));
		WebElement oopsDrop = driver.findElement(By.xpath("(//div[@class='S10qr1dFzMP0OB'])[2]"));
		WebElement sdlcDrag = driver.findElement(By.xpath("(//div[@class='amUfYqLTZOvGsn'])[2]"));
		WebElement sdlcDrop = driver.findElement(By.xpath("(//div[@class='S10qr1dFzMP0OB'])[3]"));
		Actions actions = new Actions(driver);
		actions.clickAndHold(testngDrag).release(testngDrop).build().perform();
//		actions.clickAndHold(joinsDrag).release(joinsDrop).build().perform();
		actions.clickAndHold(oopsDrag).release(oopsDrop).build().perform();
		actions.clickAndHold(sdlcDrag).release(sdlcDrop).build().perform();	
	}
	@Test(priority = 6, dependsOnMethods = "DragAndDrop")
	public void LogoutFromTrelloApplication() {
		WebElement logoutIcon = driver.findElement(By.xpath("//div[@role='presentation']//span[@title='jagruthi G (jagruthig246)']"));
		logoutIcon.click();
		WebElement logoutText = driver.findElement(By.xpath("//span[text()='Log out']"));
		logoutText.click();
		WebElement logoutButton = driver.findElement(By.xpath("//span[text()='Log out']"));
		logoutButton.click();
	}
}
