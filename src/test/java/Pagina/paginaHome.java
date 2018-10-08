package Pagina;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class paginaHome {
  
  public Date fecha;	
  public WebDriver driver;
  public JavascriptExecutor js;
  public String url = "https://www.ebay.com/";
  public By txt_buscar = By.id("gh-ac");
  public By btn_buscar = By.id("gh-btn");
  public By cbo_marca = By.id("e1-54");
  public By resultado = By.cssSelector(".rcnt");
  public By resultado_obtenido = By.cssSelector(".kwcat");
  public By lnk_registrate = By.cssSelector("#gh-ug-flex > a:nth-child(1)");
  public By txt_nombre = By.id("firstname");
  public By txt_apellido = By.id("lastname");
  public By txt_email = By.id("email");
  public By txt_clave = By.id("PASSWORD");
  public By btn_crear = By.id("ppaFormSbtBtn");
  public String myproyecto = System.getProperty("user.dir");
 
  public paginaHome(String navegador){
	  
	  switch(navegador){
	  case "firefox":
		  System.setProperty("webdriver.firefox.marionette",myproyecto+"\\drivers\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  js = (JavascriptExecutor) driver;
		  break;
	  case "chrome":
		  System.setProperty("webdriver.chrome.driver",myproyecto+"\\drivers\\chromedriver.exe");
		  driver = new ChromeDriver();
		  js = (JavascriptExecutor) driver;
		  break;
	  
	  }
	  
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }

  public String buscar_item(String nombre) throws Exception {
	  
	  String imprimir;
	  driver.get(url);
	  driver.findElement(txt_buscar).sendKeys(nombre);
	  Thread.sleep(1000);
	  driver.findElement(btn_buscar).click();
	  Thread.sleep(3000);
	  js.executeScript("window.scrollBy(0,500)");
	  Thread.sleep(8000);
	  driver.findElement(cbo_marca).click();
	  Thread.sleep(8000);
	  System.out.println("Hemos Finalizado la búsqueda...");
	  imprimir = driver.findElement(resultado).getText();
	  System.out.println("Resultado de la búsqueda para marca en " + nombre + " es: " +  imprimir);
	  Thread.sleep(1000);
	  takeScreenShotTest(driver,"buscar_item");
	  return driver.findElement(resultado_obtenido).getText();
  }
	  
  
  public void registrar_usuario(String nombre, String apellido, String email, String clave) throws Exception{
	  
	  fecha = new Date();
	  driver.get(url);
	  driver.findElement(lnk_registrate).click();
	  Thread.sleep(1000);
	  driver.findElement(txt_nombre).clear();
	  driver.findElement(txt_nombre).sendKeys(nombre);
	  driver.findElement(txt_apellido).clear();
	  driver.findElement(txt_apellido).sendKeys(apellido);
	  driver.findElement(txt_email).clear();
	  driver.findElement(txt_email).sendKeys(email);
	  driver.findElement(txt_clave).clear();
	  driver.findElement(txt_clave).sendKeys(clave);
	  Thread.sleep(1000);
	  driver.findElement(btn_crear).click();
	  Thread.sleep(3000);
	  takeScreenShotTest(driver,"registrar_usuario");
	  DateFormat hora = new SimpleDateFormat("HH:mm:ss");
	  System.out.println("La hora es: " + hora.format(fecha));
	    
  }
  
  public void takeScreenShotTest(WebDriver driver, String imageName) {
      
      File directory = new File(myproyecto + "\\imagenes");
 
      try {
         if (directory.isDirectory()) {
            File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(imagen, new File(directory.getAbsolutePath()   + "\\" + imageName + ".png"));
         } else {
            
            throw new IOException("ERROR : La ruta especificada no es un directorio!");
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
  
 
  public void cerrarPagina(){
	  if (driver != null){
		  driver.quit();
	  }
  }
    
  }



