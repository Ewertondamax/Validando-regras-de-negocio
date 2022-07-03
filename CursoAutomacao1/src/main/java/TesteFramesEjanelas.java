import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteFramesEjanelas {
	@Test
	public void deveRealizarCadastroComSucesso() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!", msg);
		alert.accept();
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);		
		
  }
	
	@Test
	public void deveInteragirComJanelas() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Dues cento?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		
	}
	 
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
		
		//--------------Validando Regras de negocio -------------------
	
  }
	
	@Test
	public void deveValidarNomeObrigatori() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		driver.quit();
  }
	
	@Test
	public void deveValidarSobrenomeObrigatori() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		driver.quit();
  }
	
	@Test
	public void deveValidarSexoObrigatori() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
				
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
		driver.quit();
  }
	
	@Test
	public void deveValidarComidaVegetariana() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		driver.quit();
  }
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file://" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		
		Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		combo.selectByVisibleText("Karate");
		combo.selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		driver.quit();
  }
	
	
	
}
