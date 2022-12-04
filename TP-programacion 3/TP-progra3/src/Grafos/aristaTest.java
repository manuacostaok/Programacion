package Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class aristaTest {
	
	
	@Test
	public void existeAristaTest()
	{
		Arista a=new Arista(1,2,3);
		assertTrue(a.getPeso()>0&&a.getDestino()>0&&a.getOrigen()>0);
	}
	
	@Test
	public void origenNegativoTest()
	{	
		Arista a=new Arista(1,-2,3);
		assertTrue(a.getOrigen()<0);
		
	}
	@Test
	public void destinoNegativoTest()
	{	
		Arista a=new Arista(1,-2,-1);
		assertTrue(a.getDestino()<0);
	}
	@Test
	public void pesoNegativoTest()
	{	
		Arista a=new Arista(-1,2,1);
		assertTrue(a.getPeso()<0);
	}
}
