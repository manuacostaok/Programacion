package Grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
	
	private int Cantvertices;
	private int cantAristas;
	private boolean [][] Vertices;
	private boolean [][] Aristas;
	private ArrayList<Arista> AristasConPeso;
	
	
	
	public Grafo(int n, int aristas) {
		this.cantAristas=aristas;
		this.AristasConPeso=new ArrayList<Arista>();
		this.Cantvertices=n;
		this.Vertices= new boolean [Cantvertices][Cantvertices];
		this.setAristas(new boolean [cantAristas][cantAristas]);
	
	}
	
	public void agregarArista(int i, int j) 
	{
		try {
		getAristas()[i][j]=true;
		getAristas()[j][i]=true;
		}
		 catch(Exception e)
		{
			 if(i<0 || j<0) 	
			 {
				 throw new IllegalArgumentException("ERROr! Vertice negativo");
			 }
		}
		
	}

	public void agregarAristaConPeso(int peso ,int origen,int destino) 
	{
		
			if(peso<0) 
			{
				throw new IllegalArgumentException("ERROr! Peso de arista negativo");		}
			
			else {
				this.AristasConPeso.add(new Arista(peso,origen,destino));
			}
	}
	public void eliminarArista(int i,int j) {
		
		VerificarVertice(i);
		VerificarVertice(j);
		VerificarLopps(i, j);
		getAristas()[i][j]= getAristas()[j][i]= false;
	}
	
	public boolean existeArista(int i, int j) {
		VerificarVertice(i);
		VerificarVertice(j);
		return getAristas()[i][j];
	}
	
	public Set<Integer> Vecinos(int i){

		VerificarVertice(i);
		Set<Integer> ret= new HashSet<Integer>();
		for (int j=0; j<Vertices.length;j++) {
			if (existeArista(i, j))
				ret.add(j);
		}
		return ret;
	}
	
	public int getMedida() {
		return this.Cantvertices;
		
	}
	
	private void VerificarVertice(int i) {
		if (i<0)
			throw new IllegalArgumentException("el vertice no puede ser negativo:"+i);
		if (i> Vertices.length-1)
			throw new IllegalArgumentException("los vertices deben estar entre 0 y |v|-1:"+i);
	}
	
	private void VerificarLopps(int i, int j) {
		if (i==j)
			throw new IllegalArgumentException("no se permiten loops"+j);
	}
	
	public int getCantVecinos(int nodo) {
		int aux=0;
		for (int i=0;i<getMedida();i++) {
			if (Vertices[nodo][i]==true)
				aux++;	
		}
		return aux;
				
	}
	
	public boolean[][] getA() {
		return Vertices;
	}
	
	public Arista getArista(int posicion){
		return this.AristasConPeso.get(posicion);
	}


	int getcantAristas() {
		int aux=this.cantAristas;
		
			return aux;
	}

	
	public ArrayList<Arista> getListaAristas(){
		
		return this.AristasConPeso;
	}
	
	
	public static void main(String[] args) {
		}

	public boolean [][] getAristas() {
		return Aristas;
	}

	public void setAristas(boolean [][] aristas) {
		Aristas = aristas;
	}

}
