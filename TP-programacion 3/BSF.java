package Grafos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BSF {
	
	private int vertices;
	private static int max=100;
	private static boolean A[][]=new boolean[max][max];
	private int padres[] = new int[20];
	private List<Integer> camino = new ArrayList<Integer>();
	
	public BSF() 
	{
		
	}
	
	public BSF(Grafo gr, int nodo_raiz, int nodo_destino)
	{
		
		
		System.out.println("asignando ");
		//System.out.println(nodo_destino+"primera entrada bsf");
		int inicio=nodo_raiz;
		int nodo_actual;
		this.vertices = gr.getMedida();
		
		boolean visitado[] = new boolean[10];
		Arrays.fill(visitado,false);
		padres[nodo_raiz]=-1;
		visitado[inicio] = true;
		Queue<Integer> cola = new LinkedList<Integer>();
		cola.add(inicio);
		//System.out.println(nodo_destino+"antes de while");
		while(!cola.isEmpty()) 
		{
			//System.out.println(nodo_destino+"en el while antes del if xd");
			nodo_actual=cola.remove();
			if(nodo_actual==nodo_destino) 
				{
				break;
				}
			//System.out.println(nodo_destino+"antes del for de los vecinos");
			for(int i=1;i<=vertices;i++)
				{	//vemos vecinos nodo actual
				//System.out.println(nodo_destino+"entro al for de los vecinos");
				System.out.println("entro al for");
				System.out.println("el vecino es:"+i);
				boolean vecino = A[nodo_actual][i];
				System.out.println("el valor de vecino es:"+vecino);
				if(vecino==true && !visitado[i])
					{		//no visitado agregamos a cola
					//System.out.println(nodo_destino+"en el vecino del actual");
						padres[i]=nodo_actual;
						cola.add(i);
						visitado[i] = true;	
					}	
				}
		}

		System.out.println("Recorrido de nodos para llegar de nodo "+nodo_raiz+" a " +nodo_destino);
	
		for( ;; ){
			//System.out.println("for recu");
			camino.add( nodo_destino );
			//System.out.println("nodo_destino es :"+nodo_destino);
			if( padres[ nodo_destino ] == -1 )break;
			//System.out.println(nodo_destino);
			nodo_destino = padres[ nodo_destino ];
		}

		for( int i = camino.size() - 1 , k = 0 ; i >= 0 ; --i ){
			//System.out.println("for pa tras");
			if( k != 0 ) System.out.print( "->");
			System.out.print( camino.get( i ) );
			k = 1;
		}
		System.out.println();
	}
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("Bienvenido");
		Grafo g=new Grafo(7,10);
		
		int[] origen=  {1,1,2,2,3,4,4,5,5,6};
		int[] destino= {2,3,4,5,2,5,7,6,7,7};
		for( int i = 0 ; i < g.cantAristas() ; ++i ){
			Scanner sc=new Scanner(System.in);
			//System.out.println("origen de arista");
			//int u = sc.nextInt();
			//System.out.println("destino de arista");
			//int	v = sc.nextInt(); //enlace origen - destino
			A[ origen[i] ][ destino[i] ] = true;
			
		
			//g.SetposDeA(origen[i],destino[i] ,true);
			
		}
		BSF ejemplo=new BSF(g,1,6);
		
		

	}

}
