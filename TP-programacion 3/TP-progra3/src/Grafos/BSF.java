package Grafos;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BSF {
		
		private int vertices;
		private int padres[] = new int[20];
		private List<Integer> camino = new ArrayList<Integer>();
		
		public BSF() 
		{
			
		}
		
		public BSF(Grafo gr, int nodo_raiz, int nodo_destino)
		{		
		try 
		{
			System.out.println("asignando ");
			int inicio=nodo_raiz;
			int nodo_actual;
			this.vertices = gr.getMedida();		
			boolean visitado[] = new boolean[10];
			Arrays.fill(visitado,false);
			padres[nodo_raiz]=-1;
			visitado[inicio] = true;
			Queue<Integer> cola = new LinkedList<Integer>();
			cola.add(inicio);
			while(!cola.isEmpty()) 
			{
				nodo_actual=cola.remove();
				if(nodo_actual==nodo_destino) 
					break;	
				for(int i=0;i<=vertices;i++)
					{	
					boolean vecino= gr.getAristas()[nodo_actual][i];					
					if(vecino==true && !visitado[i])
						{		
							padres[i]=nodo_actual;
							cola.add(i);
							visitado[i] = true;	
						}	
					}
			}

			System.out.println("Recorrido de nodos para llegar de nodo "+nodo_raiz+" a " +nodo_destino);
		
			for(;;){
				camino.add(nodo_destino);
				if(padres[nodo_destino] == -1)
					break;
				nodo_destino = padres[ nodo_destino ];
			}
			for( int i = camino.size() - 1 , k = 0 ; i >= 0 ; --i ){				
				if( k != 0 ) System.out.print( "->");
				System.out.print( camino.get( i ) );
				k = 1;
			}
			System.out.println();
	
		}
		catch(Exception e)
			{
				 if(nodo_raiz<0 || nodo_destino<0 ||nodo_raiz>gr.getMedida()||nodo_destino>gr.getMedida()) 	
				 {
					 throw new IllegalArgumentException("ERROR 404: Vertice/Arista negative");
					
				 }
			}
	
		}
		public static void main(String[] args) 
		{
			

		}



}
