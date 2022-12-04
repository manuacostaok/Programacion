package juego;

import java.util.Random;

import java.awt.Image;

import entorno.Entorno; 

public class Enjambre {
	private Spider[] aras;


	public Enjambre(Entorno e,int cant, String soloparalistavacia) {
		aras= new Spider[cant];
	}

	public Enjambre(Entorno e,int cantaras) {
		aras = new Spider[cantaras];
		for(int i =0; i<aras.length; i++) {
			aras[i]=new Spider(e);
		}
	}
	public void dibujar(Entorno e, Image aranas) {
		for(int i =0; i<aras.length; i++) {
			if(aras[i]!=null) {
				aras[i].dibujar(e,aranas);
			}
		}
	}

	//Saca a las ara�as del borde de los edificios
	public void chocanedificios(Ciudad c, Exterminador e) {
		for(int i =0; i<aras.length; i++) {
			if(aras[i]!=null) {
				if(c.chochaste(aras[i])) {
					for(int j=0; j<c.array().length;j++ )
						aras[i].salirdelborde(c.array()[j]);
					//aras[i].chocar(e.angulo(aras[i]), e);
				}		
			}
		}

	}


	//hace que las ara�as siga a los jugadores
	public void mover(Exterminador e,Entorno entorno, Image aranas) {
		if(e!=null) {
			for(int i =0; i<aras.length;i++) {
				if(aras[i]!=null) {	
					aras[i].mover(e.angulo(aras[i]));
					aras[i].dibujar(entorno,aranas);
				}
			}
		}

	}
	
	//devuelve verdadero si una ar�as colisona con un "Disparo"
	public boolean recibirdisparo( Disparar bala) {
		if(bala!=null) { 
			for(int i =0; i<aras.length;i++) {
				if(aras[i]!=null&& bala!=null && bala.bala()!=null) {
					if(aras[i].recibedisparo(bala)) {
						aras[i]=null;
						bala.hacernull();
						return true;
					}
				}
			}
		}
		return false;
	}
	public Spider[] get_arrayAra�as() {
		return aras;
	}
	//Devuelve el indice de una ara�a que piso una "Mine" sino se pisa ninguna devuelve 10000 un numero grande fuera del rango de "Enjambre"
	public int ara�aPisoMinaIndice(Campominado c) {
		if(c!=null) {
			for(int i =0; i<aras.length;i++) {
				if(aras[i]!=null) {
					if(c.indiceminapisada(aras[i])<c.arrayminas().length)
						return i; 
				}

			}
		}
		return 10000;
	}
	
	public void HacerNullAra�a(int ara�a) {

		aras[ara�a]=null;
	}
	//Devuelve verdadero si la ara�a colisiona con el jugador
	public boolean ara�aTocoJugador(Exterminador e) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null) {
				if(e.perdiste(aras[i])){
					return true;
				}
			}
		}
		return false;
	}
	
	//Se fija si hay una ara�a cerca del radio de la explosion de una "Mine" y devuelve el indice
	public int ara�aCercaRadioExp(Mine m) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null && m!=null) {
				if(aras[i].distancia(m)<=1){
					return i;
				}
			}
		}
		return 10000;
	}
	
	//Hace null la ara�a que este cerca del rango de explosion
	public void ara�aCercaRadioExpNullSolo(Spider s) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null && aras[i]!=s) {
				if(aras[i].distancia(s)<=3){

					aras[i]=null;
				}
			}
		}

	}
	

	//Crea una ara�a en el arreglo 
	public void spawnAra�a(Entorno e) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]==null) {
				aras[i]=new Spider(e);
				break;
			}
		}
	}
	
	//Crea ara�a donde murio "ArasGigantes"
	public void agregarAra�aBoss(Entorno e,Punto boss) {
		if(boss!=null&&this!=null) {
			for(int i =0; i<aras.length;i++) {
				if(aras[i]==null) {
					aras[i]=new Spider(boss.x,boss.y,0,0.7,2);
					break;
				}
			}
		}
	}
//Reaparece las ara�as en un tiempo random
	public void spawnAra�asnull(Enjambre en, Entorno entorno, int tiempo){
		Random generador = new Random ();
		int aparicionara�as = generador.nextInt(6000);
		for(int i=0; i<aras.length;i++){
			if(aparicionara�as!=0) {
				if(en.get_arrayAra�as()[i]==null && tiempo % aparicionara�as ==0 ) {
					en.spawnAra�a(entorno);

				}
			}

		}
	}
	//controla si una ara�a esta dentro del rango de explosion
	public int ara�aCercaDeMinaQueExplota(Mine m) {
		for(int i=0; i<aras.length;i++){
			if(aras[i]!=null) {
				if(aras[i].distancia(m)<1) {
						return i;
				}
			}
		}
		return 10000;
	}

}
