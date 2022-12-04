package Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class BSFTest {

	@Test(expected=IllegalArgumentException.class)
	public void BSFnodoOrigenInvalidoTest() {
		
		Grafo gr= new Grafo(4,3);
		BSF ej=new BSF(gr,-1,5) ;
		
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void BSFnodoDestinoInvalidoTest() {
		
		Grafo gr= new Grafo(4,3);
		BSF ej=new BSF(gr,2,-5) ;
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void BSFnodoDestinofueraDeRangoTest() {
		
		Grafo gr= new Grafo(4,3);
		BSF ej=new BSF(gr,2,25) ;
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void BSFnodoOrigenFueraDeRangoTest() {
		
		Grafo gr= new Grafo(4,3);
		BSF ej=new BSF(gr,20,3) ;
		
	}
	
	
	
}
