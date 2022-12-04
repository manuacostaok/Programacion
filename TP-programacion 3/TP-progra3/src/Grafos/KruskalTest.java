package Grafos;

import static org.junit.Assert.*;

import org.junit.Test;

public class KruskalTest {

	@Test
	public void KruskalValidotest() {		
		Grafo gr= new Grafo(7,10);
		gr.agregarAristaConPeso(1, 7, 1);
		for(int i=0;i<gr.getListaAristas().size();i++) {	
			assertTrue(gr.getListaAristas().get(i).getPeso()>0);
		}	
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void KruskalInvalido() {
		 Grafo grafo = new Grafo(10,5);
		 grafo.agregarAristaConPeso(-1,3,1);
		 Kruskal k=new Kruskal(grafo);

	}

	@Test(expected=IllegalArgumentException.class)
	public void KruskalsinAristas() {		
		Grafo gra= new Grafo(7,0);
		Kruskal k=new Kruskal(gra);
	}
}
