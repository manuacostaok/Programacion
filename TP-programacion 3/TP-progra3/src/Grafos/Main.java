package Grafos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	
	public Main() {}
	
	public static Grafo aleatorio(int n) 
	{
		Random ran=new Random();
		int cantMaxAristas = n*(n-1);
		int m=ran.nextInt(cantMaxAristas); //cant 
		Grafo gr=new Grafo(n,m);
		for(int i=0;i<m;i++) {
			int a=ran.nextInt(n);
			int b=ran.nextInt(n);
			int c=ran.nextInt(100);
			if (c==0)
				c=1;
			gr.agregarArista(a, b);
			gr.agregarAristaConPeso(c, a,b );
		}
		
		
		
		return gr;
	}
	
	public static void MostrarTiempoDeEjecucion(ArrayList<String> lista) 
	{
		for(int i=0;i<lista.size();i++) 
		{
			System.out.println(lista.get(i));
		}
		
	}
	
	
	public static void main(String[] args) 
	{

		Scanner scan=new Scanner(System.in);
		
		//-------------------------------------------------------------------------------------------------------
		ArrayList<String> tiemposDeEjecucionKRK=new ArrayList(); 
		ArrayList<String> tiemposDeEjecucionBSF=new ArrayList();
		int tamanioBSF=5;
		int tamanioKRK=5;
		System.out.println("Bienvenido");
		

		//KRUSKAL
		System.out.println("iniciamos recorrido kruskal");
		System.out.println("buscando el AGM");
		
		for (int j=1;j<11;j++) 
		{
			tamanioKRK=tamanioKRK+20;
			long inicio=System.currentTimeMillis();
			
			
			Grafo g=aleatorio(tamanioKRK);
		
			System.out.println("el grafo tiene:"+g.getMedida()+" nodos y "+g.getcantAristas()+" aristas");
			System.out.println("el grafo es el siguiente");
			System.out.println("Origen ==>> Destino :Valor");
			
					
			Kruskal ejemploo=new Kruskal(g);
			
			//MEDIR TIEMPO DE EJECUCION
			long fin=System.currentTimeMillis();
			double tiempo=(fin-inicio)/1000.0;
			tiemposDeEjecucionKRK.add("Grafo nª  " +j+" Tiene "+g.getMedida()+" vertices,"+" con"+g.getcantAristas()+" aristas "+". Tardo " +tiempo+" segundos en ejecutar KRUSKAL");
			
		}
		
		
		//bsf
		System.out.println("iniciamos recorrido BSF");
		
		
		for (int i=1;i<11;i++) 
		{
			long iniciobsf=System.currentTimeMillis();
			tamanioBSF=tamanioBSF+20;
			Grafo g=aleatorio(tamanioBSF);

			
			System.out.println("el grafo tiene:"+g.getMedida()+" nodos y "+g.getcantAristas()+" aristas");
			System.out.println("el grafo es el siguiente");
			System.out.println("Origen ==>> Destino :Valor");
		
					
//			System.out.println("ingrese el nodo origen");
			Random ran=new Random();
			int INICIO=ran.nextInt(tamanioBSF);
//			System.out.println("ingrese el nodo objetivo");
			int objetivo=ran.nextInt(tamanioBSF);			
			while(objetivo>tamanioBSF) {
				System.out.println("ingrese el nodo objetivo valido,de 0 a"+tamanioBSF);
				objetivo=scan.nextInt();
			
			}
			BSF ejemplo=new BSF(g,INICIO,objetivo);
			
			//MEDIR TIEMPO DE EJECUCION
			long finbsf=System.currentTimeMillis();
			double tiempobsf=(finbsf-iniciobsf)/1000.0;
			tiemposDeEjecucionBSF.add("Grafo nª  " +i+" Tiene "+g.getMedida()+" vertices,"+" con "+g.getcantAristas()+" aristas "+". Tardo " +tiempobsf+" segundos en ejecutar BSF");
			
		}
		mostrarDatos(tiemposDeEjecucionKRK);
		mostrarDatos(tiemposDeEjecucionBSF);
		
		}

	private static void mostrarDatos(ArrayList<String> tiemposDeEjecucionBSF) {
		for(int i=0;i<tiemposDeEjecucionBSF.size();i++)
		{
			String s=tiemposDeEjecucionBSF.get(i);
			
			System.out.println(s);
		}
	}
}
