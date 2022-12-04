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

	//Saca a las arañas del borde de los edificios
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


	//hace que las arañas siga a los jugadores
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
	
	//devuelve verdadero si una arñas colisona con un "Disparo"
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
	public Spider[] get_arrayArañas() {
		return aras;
	}
	//Devuelve el indice de una araña que piso una "Mine" sino se pisa ninguna devuelve 10000 un numero grande fuera del rango de "Enjambre"
	public int arañaPisoMinaIndice(Campominado c) {
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
	
	public void HacerNullAraña(int araña) {

		aras[araña]=null;
	}
	//Devuelve verdadero si la araña colisiona con el jugador
	public boolean arañaTocoJugador(Exterminador e) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null) {
				if(e.perdiste(aras[i])){
					return true;
				}
			}
		}
		return false;
	}
	
	//Se fija si hay una araña cerca del radio de la explosion de una "Mine" y devuelve el indice
	public int arañaCercaRadioExp(Mine m) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null && m!=null) {
				if(aras[i].distancia(m)<=1){
					return i;
				}
			}
		}
		return 10000;
	}
	
	//Hace null la araña que este cerca del rango de explosion
	public void arañaCercaRadioExpNullSolo(Spider s) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]!=null && aras[i]!=s) {
				if(aras[i].distancia(s)<=3){

					aras[i]=null;
				}
			}
		}

	}
	

	//Crea una araña en el arreglo 
	public void spawnAraña(Entorno e) {
		for(int i =0; i<aras.length;i++) {
			if(aras[i]==null) {
				aras[i]=new Spider(e);
				break;
			}
		}
	}
	
	//Crea araña donde murio "ArasGigantes"
	public void agregarArañaBoss(Entorno e,Punto boss) {
		if(boss!=null&&this!=null) {
			for(int i =0; i<aras.length;i++) {
				if(aras[i]==null) {
					aras[i]=new Spider(boss.x,boss.y,0,0.7,2);
					break;
				}
			}
		}
	}
//Reaparece las arañas en un tiempo random
	public void spawnArañasnull(Enjambre en, Entorno entorno, int tiempo){
		Random generador = new Random ();
		int aparicionarañas = generador.nextInt(6000);
		for(int i=0; i<aras.length;i++){
			if(aparicionarañas!=0) {
				if(en.get_arrayArañas()[i]==null && tiempo % aparicionarañas ==0 ) {
					en.spawnAraña(entorno);

				}
			}

		}
	}
	//controla si una araña esta dentro del rango de explosion
	public int arañaCercaDeMinaQueExplota(Mine m) {
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
