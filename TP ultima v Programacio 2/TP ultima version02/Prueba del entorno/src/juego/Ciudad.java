package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;

public class Ciudad {
	private int edificio;
	private Edificio[] edificios;
	Ciudad(){
		Random generador = new Random ();
		int cantidad=0;
		while(cantidad<4) {
			cantidad= generador.nextInt(8);	
		}
		edificios= new Edificio[cantidad];
			for (int i =0; i<cantidad;i++){
				int x = generador.nextInt(600),y = generador.nextInt(400);
				int alto = 100, ancho = 100;
				/*while (alto < 100 && ancho < 100) {
					alto = generador.nextInt(200);
					ancho = generador.nextInt(200);
				}*/
				 edificios[i]=new Edificio(x,y,alto,ancho);	 
			}	
			fijarse();
			while(edificio<=cantidad) {
			for (int i = 0; i < edificios.length; i++) {
				if(edificios[i]==null) {
					edificios[i]=new Edificio();
					fijarse();
					edificio=0;
				}
				if(edificios[i]!=null) {
					edificio++;
				
				}
			}
			}
			
		}
	public void dibujar(Entorno e, Image edificio) {
		for(int i=0; i<edificios.length;i++) {
			if(edificios[i]!=null) {
			edificios[i].dibujar(e, edificio);
			}
			
		}
	}
	 public boolean chochaste(Spider s) {
		 for(int i =0; i<edificios.length;i++) {
			 if(edificios[i]!=null) {
			 if (s.chocaste(edificios[i])) {
				
				 return true;
			 }
		 }
		 }
		 return false;
	 }
	 public boolean chocar(Exterminador e) {
		 if(e!=null) {
		 for(int i =0; i<edificios.length;i++) {
			 if(e.chocaste(edificios[i])) {
				 e.salirdelborde(edificios[i]);
				 return true;
			 }
		 }
		 }
		 return false;
	 }
	 
	 public boolean choqueedificios(Disparo bala) {
		 for(int i =0; i<edificios.length;i++) {
			 if(edificios[i]!=null && bala!=null) {
				 if(bala.chocaste(edificios[i])) {
					 return true;
				 }
			 }
		 }
		 return false;
	 }
	 public Edificio[] array() {
		 return edificios;
	 }
	 private void fijarse() {
			for (int i=0; i<edificios.length;i++) {
				for (int j =0; j<edificios.length;j++) {
					if(edificios[i]!=edificios[j]&& edificios[i]!=null&& edificios[j]!=null) {
						if(edificios[i].chocaste(edificios[j])) {
							edificios[j]=null;
						}
					}
				}
			}
			
		}
}


