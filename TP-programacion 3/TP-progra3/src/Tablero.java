import java.util.Random;

public class Tablero {
	
	

	int alto; //al alto es un numero >0
	int ancho; //al ancho es un numero >0
	boolean [][] tablero; //tablero de enteros con valor 0 si esta vacio o{1,2} si esta ocupado
	                  //nuemro de filas=alto y numero de columnas=ancho
	
	
	//Constructor del tablero sin parametros
	public Tablero() {
		this.alto=0;
		this.ancho=0;
		this.tablero=new boolean [alto][ancho];	
	}
	
	
	//Constructor del tablero con parametros dados por el usuario
	Tablero(int Al, int An){
		
		alto=Al;
		ancho=An;
		
		this.tablero=new boolean [alto][ancho]; //arreglo de booleanos que representa el tablero true para encendido false para apagado
		for (int k=0;k<this.tablero.length;k++) {
			for (int z=0;z<this.tablero[k].length;z++) {
					this.tablero[k][z]=false;				
				}
			}	
		}
	
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------------
	//Este bloque se pasa a la clase que controla la funcionalidad del juego
	
	//metodo que cambia el estado de la luz en la posicion indicada por fila y col
	//si la posicion esta encendida se apaga y viceversa
//	public void EncenderLuz(int fila , int col) {
//		
//		CambiarEstado(fila ,col); //posision selecionada
//		
//		//posiciones que estan al rededor de la posicion seleccionada tmb son afectadas por el cambio de estado de la luz
//		CambiarEstado(fila ,col+1);
//		CambiarEstado(fila ,col-1);
//		CambiarEstado(fila-1 ,col);
//		CambiarEstado(fila+1 ,col);
//		}
	//-------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//funcion local que cambia el estado true o false del tablero y es llamada por EncenderLuz 
	public void CambiarEstado(int fila , int col){
		if ((fila>=0 & fila<=tablero.length-1) & (col>=0 & col<=tablero[0].length-1)){
		
			if (this.tablero[fila][col]==true) {
				this.tablero[fila][col]=false;
				System.out.println("se cambio a false");
				}
		
			else{
				this.tablero[fila][col]=true;
				System.out.println("se cambio a true");
				}
			}
		else
			System.out.println("la fila o culumna no existe");
		}
	

	
	//-------------------------------------------------------------------------------------------------------------------
	//Este bloque se pasa a la clase que controla la funcionalidad del juego
		
//	public boolean VerificarGanador(){
//		
//		for (int k=0;k<this.tablero.length;k++) {
//			for (int z=0;z<this.tablero[k].length;z++) {
//				if (this.tablero[k][z]==false) {
//					cont=cont+1;
//					cont2++;
//					System.out.println("luz apagada,el numero de luces apagadas es: "+cont2);
//					}
//				else {
//					cont=0;
//					System.out.println(("la luz esta encendida"));
//					}
//			}}
//		if (cont==this.tablero.length*this.tablero[0].length) {
//			System.out.println("¡¡se termino el juego!!");
//			return true;
//			}
//		else {
//			System.out.println("sigue intentanto");
//			return false;
//			}
//	}
//	
	//--------------------------------------------------------------------------------------------------------------------------------
	
	
	public int getAlto(){
		
		return this.alto;	
	}
	
	public int getAncho(){
		return this.ancho;
	}
	
	public boolean getPos(int fila,int columna) {
		return this.tablero[fila][columna];
	}
	
	public void setPos(int fila, int columna, boolean b) {
		
		this.tablero[fila][columna]=b;
	}
	
	
	//tostring que se utiliza para imprimir el estado del tablero 
	public String toString() {
		
		StringBuilder str=new StringBuilder();
		String mje="";
		for(int i=0;i<(tablero.length);i++) {
			if(i<10)
				mje=mje+"fila "+i;
			else
				mje=mje+"fila"+i;
			for(int j=0;j<tablero[i].length;j++) {
				
				 mje=mje+"|"+tablero[i][j]+"|";
				}
			mje=mje+"\n";
			str.append(mje);
			mje="";	
		}
		return str.toString();
	}


	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//simulador de juego
//		Random ran=new Random();
//		Random ran2=new Random();
//		
//		Tablero tab= new Tablero(4,4);
//		System.out.println(tab.toString());
//		boolean bandera=true;
//		while(bandera) {
//			int num1=ran.nextInt(4);
//			int num2=ran.nextInt(4);
//			
//			tab.EncenderLuz(num1,num2);
//			System.out.println(tab.toString());
//			tab.VerificarGanador();
//			if (tab.VerificarGanador()==true)
//				bandera=false;
//		}
		
	}


	

}

