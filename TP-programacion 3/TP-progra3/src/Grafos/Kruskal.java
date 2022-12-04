package Grafos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	
	private int max=1000;
	private int padres[]= new int[max];
	private int peso,origen,destino;
	private ArrayList<Arista> aristas=new ArrayList<Arista>();
	//---------------------------------------------------------------------

	public Kruskal(Grafo gr)
	{  
		
		seteoPadres();
		int peso_total=0;  
		int cantAristas=0; 
		int contador=0;    
		
		
			if(gr.getListaAristas().size()>0) {
				this.aristas=gr.getListaAristas();
	}
			else {
				
			System.out.println("Agrege aristas al grafo y luego ejecute Kruskal");
			
				throw new IllegalArgumentException("el grafo no tiene aristas");
			}
		
		
		Collections.sort(this.aristas,new Comparator<Arista>() 
		{
			@Override
			public int compare(Arista o1,Arista o2) 
			{
				return o1.getPeso()-o2.getPeso();
			}
		});
		
		imprimirGrafo(gr.getMedida(),gr.getcantAristas(),aristas);
		System.out.println("El Arbol generador minimo es el siguiente:");
		System.out.println("");
		while((cantAristas<gr.getMedida()-1)&&(contador<gr.getcantAristas())) 
		{
	
			origen= aristas.get(contador).getOrigen();
			destino= aristas.get(contador).getDestino();
			peso=aristas.get(contador).getPeso();
			if (!mismaComponente()) 
			{
				union(origen,destino);
				peso_total+=peso;
				System.out.println(" "+origen+" ==>> "+destino+"  :"+peso);
				cantAristas++;
			}
			contador++;
		}
	 
		 System.out.println("\nEl costo encontrado es de :"+peso_total);
		 System.out.println("cantidad de pasos del AGM :"+cantAristas);
		

		
		
		}


	private boolean mismaComponente() 
	{
		return find(origen)==find(destino);
	}
		
	
	public int find(int x) 
	{
		if (padres[x]==x) 
		{
			return x;
		}
		return find(padres[x]);
	}
	
	public void union(int x,int y) 
	{
		int PadreX=find(x);
		int PadreY=find(y);
		padres[PadreX]=PadreY;
	}
	
	
	private void imprimirGrafo(int nodos2, int aristas2, ArrayList<Arista> arcos2) 
	{
		for(int i=0;i<aristas2;i++) 
		{
			int origenn= aristas.get(i).getOrigen();
			int destinoo= aristas.get(i).getDestino();
			int pesoo=aristas.get(i).getPeso();
			System.out.println(""+(origenn)+" ==>> "+(destinoo)+", :"+pesoo);
		}
		
	}

	private void seteoPadres() 
	{
		for(int i=0;i<max;i++) 
		{
			padres[i]=i;
		}
	}
	
	
	
	public static void main(String[] args) 
	{
		
	}
}
	
	
