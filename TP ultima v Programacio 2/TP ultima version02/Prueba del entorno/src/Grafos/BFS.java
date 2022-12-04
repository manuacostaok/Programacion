package Grafos;


import java.util.*;

import Grafo;

public class BFS {
	static final int MAX = 10;
	static int A[][] = new int[ MAX ][ MAX ];     //matriz de adyacencia
	static Scanner sc = new Scanner( System.in );
	static int Vertices, padres[] = new int[ MAX ];

	public static void bfs(Grafo gr, int nodo_raiz, int nodo_destino){
		int pasos = 0, max = 0, nodo_actual;
		boolean visitado[ ] = new boolean[ MAX ];
		Arrays.fill( visitado , false );
		padres[ nodo_raiz ] = -1;
		visitado[ nodo_raiz ] = true;
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add( nodo_raiz );
		while(!Q.isEmpty()){
			max = Math.max( max , Q.size() );				//ver memoria usada en cola
			nodo_actual = Q.remove();
			pasos++;										//numero de vertices que estoy visitando
			if( nodo_actual == nodo_destino )break;						//si se llego al destino
			for( int i = 1 ; i <= Vertices ; ++i ){	    	        //vemos adyacentes a nodo actual
				int v = A[ nodo_actual ][ i ];
				if( v != 0 && !visitado[ i ] ){				//no visitado agregamos a cola
					System.out.println( nodo_actual +" -> "+ i); //vemos recorrido de todo BFS
					padres[ i ] = nodo_actual;						//para ver recorrido mas corto de nodo inicio a fin
					visitado[ i ] = true;
					Q.add( i );
				}
			}
		}
		System.out.println("Memoria maxima: " + max );
		System.out.println("Nro Pasos: " + pasos);
		PrintRecorrido( nodo_raiz , nodo_destino );
	}
	
	//Imprimimos recorrido para llegar de nodo ini a fin
	static void PrintRecorrido( int ini , int fin ){

		System.out.println("Recorrido de nodos para llegar de nodo "+ini+" a " +fin);
		List<Integer> camino = new ArrayList<Integer>();

		for( ;; ){
			camino.add( fin );
			if( padres[ fin ] == -1 )break;
			fin = padres[ fin ];
		}
		for( int i = camino.size() - 1 , k = 0 ; i >= 0 ; --i ){
			if( k != 0 ) System.out.print( "->");
			System.out.print( camino.get( i ) );
			k = 1;
		}
		System.out.println();
	}
	
	public static void main( String args[] ){
		int E , u , v;
		Vertices = sc.nextInt();   //Numero de vertices
		E = sc.nextInt();	//Numero de aristas

		for( int i = 0 ; i < E ; ++i ){
			u = sc.nextInt(); v = sc.nextInt(); //enlace origen - destino
			A[ u ][ v ] = 1;
		}
		bfs();
	}
}