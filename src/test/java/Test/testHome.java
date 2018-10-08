package Test;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import Pagina.paginaHome;

public class testHome {
  
  public paginaHome paginahome;
	
  @BeforeClass
  @Parameters("navegador")
  public void inicioClase(String navegador) throws Exception{
	  paginahome = new paginaHome(navegador);
  }

  @Test
  public void buscar_flujobasico() throws Exception {
		try {
			
			String valor_esperado = "resultados para zapatos";
			String valor_obtenido = paginahome.buscar_item("zapatos");
			Assert.assertEquals(valor_esperado, valor_obtenido);
					
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
  }
  
  @Test
  public void registro_usuario() throws Exception{
	  
	  try {
		  paginahome.registrar_usuario("Alberto", "Beingolea", "qa9@ecodigital.pe", "Comercio*1");
		  	  
	  } catch (Exception e){
		  e.printStackTrace();
	  }
	    
  }
  
  @AfterClass
  public void finClase() {
	  paginahome.cerrarPagina();
  }

}
