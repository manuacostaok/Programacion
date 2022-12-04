import java.util.Random;
import java.util.Scanner;

public class JuegoDeLuces {
	
	private Tablero tab;
	private int turno;
	private int cont;
	private int cont2;
	private int tamaño;
	
	public JuegoDeLuces() {	
	tab = new Tablero();
	turno=0;
	cont=0;
	cont2=0;
	tamaño=0;
	
	}
	//Constructor con parametros
	public JuegoDeLuces(int tamaño) {
		tab = new Tablero(tamaño,tamaño);
		turno=0;
		cont=0;
		cont2=0;
		this.tamaño=tamaño;
	}
	
	
	//metodo que se encarga de mesclar las posiciones del tablero a encendidas y apagadas mediante un numero random 
	public void mezclarLuces() {
//		for (int i=0;i<tab.getAlto();i++) {
//			for(int j=0;j<tab.getAncho();j++) {
//				if(Math.random()>0.3) {
//					tab.setPos(i, j,true);
//				}
//			}
//		}
		Random ran=new Random();
		for (int i=0;i<2;i++) {
			int a=ran.nextInt(4);
			int b=ran.nextInt(4);
			EncenderLuz(a, b);
		}
	}
	
	
	
	//metodo que cambia el estado de la luz en la posicion indicada por fila y col
		//si la posicion esta encendida se apaga y viceversa
	public void EncenderLuz(int fila , int col) {
		
		tab.CambiarEstado(fila ,col); //posision selecionada
		
		//posiciones que estan al rededor de la posicion seleccionada tmb son afectadas por el cambio de estado de la luz
		tab.CambiarEstado(fila ,col+1);
		tab.CambiarEstado(fila ,col-1);
		tab.CambiarEstado(fila-1 ,col);
		tab.CambiarEstado(fila+1 ,col);
		}
	

	
// VerificarGanador recorre las posiciones del tablero buscando que todas ellas esten apagadas si ese es el caso
	// retorna true y notifica que el juego se ah terminado.
	//de lo contrario retorna false
	public boolean VerificarGanador(){
		
		for (int k=0;k<this.tab.getAlto();k++) {
			for (int z=0;z<this.tab.ancho;z++) {
				if (this.tab.getPos(k, z)==false) {
					cont=cont+1;
					cont2++;
					System.out.println("luz apagada,el numero de luces apagadas es: "+cont2);
					}
				else {
					cont=0;
					cont2=0;
					System.out.println(("la luz esta encendida"));
					}
			}}
		if (cont>=this.tab.alto*this.tab.ancho) {
			System.out.println("¡¡se termino el juego!!");
			return true;
			}
		else {
			System.out.println("sigue intentanto");
			return false;
			}
	}
	
	
	
	public String toString() {
		
		
			String g="";
			for (int i=0;i<tab.getAlto();i++) {
				for(int j=0;j<tab.getAncho();j++) {
					if(tab.getPos(i, j)) {
						g+="1";
					}
					else {
						g+="0";
					}
				}
				g+="\n";
			}
			return g;
			
		}
	
	

	public boolean getPosDelTablero(int a ,int b) {
		boolean aux;
		if (this.tab.getPos(a, b))
			aux=true;
		else
			aux=false;
		
		return aux;
	}
	
	public int getTamaño() {
		return this.tamaño;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//simulador de juego modo automatico
//				Random ran=new Random();
//				Random ran2=new Random();
//				
//				JuegoDeLuces luz= new JuegoDeLuces(4);
//				System.out.println(luz.toString());
//				boolean bandera=true;
//				while(bandera) {
//					int num1=ran.nextInt(4);
//					int num2=ran.nextInt(4);
//					
//					luz.EncenderLuz(num1,num2);
//					System.out.println(luz.toString());
//					luz.VerificarGanador();
//					if (luz.VerificarGanador()==true)
//						bandera=false;
//				}
				
				
		//------------------------------------------------------------
		//simulador con usuario
				Scanner sc = new Scanner(System.in);
				
				JuegoDeLuces juego= new JuegoDeLuces(4);
				juego.mezclarLuces();
				System.out.println(juego);
				
				while(true) {
				System.out.println("QUE LUZ APAGAS? POSICION ALTO (X)?");
				int x=sc.nextInt();
				System.out.println("QUE LUZ APAGAS? POSICION ANCHO (Y)?");
				int y=sc.nextInt();
				juego.EncenderLuz(x, y);
				System.out.println(juego);
				}
			
	}

}
