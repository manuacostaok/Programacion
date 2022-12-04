package Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class GrafoTest {
	@Test(expected=IllegalArgumentException.class)
	public void verticeNegativoTest() {
		 Grafo grafo = new Grafo(5,7);
		 grafo.agregarArista(-1, 3); 
	}
	@Test
	public void eliminarAristaExistenteTest(){
		//set up test
		Grafo grafo = new Grafo(5,5);
		// parte que quiero testear
		grafo.agregarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse(grafo.existeArista(2, 4));
	}
	
	@Test
	public void eliminarAristaInexistenteTest(){
		//set up test
		Grafo grafo = new Grafo(5,10);
		grafo.agregarArista(2, 4);
		// parte que quiero testear
		grafo.eliminarArista(3, 4);
		assertFalse(grafo.existeArista(3, 4));
	}
	
	@Test
	public void aristaExistenteTest() 
	{
		Grafo grafo=new Grafo(5,4);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(2,3));
	}

	@Test
	public void aristaOpuestaTest() 
	{
		Grafo grafo=new Grafo(5,7);
		grafo.agregarArista(2, 3);
		assertTrue(grafo.existeArista(3,2));
	}
	@Test
	public void aristaInexistenteTest() 
	{
		Grafo grafo=new Grafo(5,8);
		grafo.agregarArista(2, 3);
		assertFalse(grafo.existeArista(1,4));
	}
}
