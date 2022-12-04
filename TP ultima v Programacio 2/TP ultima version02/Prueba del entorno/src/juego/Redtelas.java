package juego;

import java.awt.Image;

import entorno.Entorno;

public class Redtelas {
	private Telara�a[] telas;
	
	public Redtelas(int cantidad) {
		telas = new Telara�a[cantidad];
		}
	//dibuja las telara�as con una imagen si es distinto de null
	public void dibujartelas(Entorno e, Image tela) {
		for (int i =0; i<telas.length;i++) {
			if (telas[i]!=null) {
				telas[i].dibujar(e, tela);
		}
	}
	}
	
	//comprueba el tiempo en el que tiene que aparecer la telara�a
	public void comprobartiempo(int tiempo) {
		for (int i =0; i<telas.length;i++) {
			if (telas[i]!=null) {
				if(tiempo>telas[i].t()) {
					telas[i]=null;
			}
			}
		}
	}
	//Crea el objeto "Telara�a" en el arreglo
	public void creartela(int Tiempo, Enjambre e) {
		for (int i =0; i<telas.length;i++) {
			if (telas[i]==null) {
				telas[i]=new Telara�a(e,Tiempo);
				
				break;
			}
	}
	}
	
	//Devuelve true si el exterminador colisiona con una "Telara�a"
	public boolean tocajugador(Exterminador e) {
	if(e!=null) {
		for (int i =0; i<telas.length;i++) {
			if (telas[i]!=null) {
			if(e.chocastecon(telas[i])) {
				return true;
			}
			}
		}
		}
		return false;
	}
	
}
