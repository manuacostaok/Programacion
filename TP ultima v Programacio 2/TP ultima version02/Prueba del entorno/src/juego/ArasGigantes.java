package juego;

import entorno.Entorno;
import java.awt.Image;
public class ArasGigantes {
	private Spider[] bosses;
	private int[] disparosboss;
	
	public ArasGigantes(int cant, Entorno e) {
		this.bosses= new Spider[cant];
		this.disparosboss = new int[cant];
		for (int i = 0;i<cant;i++) {
			bosses[i]=new Spider(e,12);
			//disparosboss[i]=0;
		}
	}
	public void dibujar(Entorno e, Image imagen) {
		for(int i =0; i<bosses.length; i++) {
			if(bosses[i]!=null) {
			bosses[i].dibujarboss(e,imagen);
			}
		}
	}
	
	//Controla la colision de objetos "Spider" con la clase "Ciudad"  
	public void chocanEdificios(Ciudad c) {
		if(this!=null){
		for(int i =0; i<bosses.length; i++) {
			if(bosses[i]!=null) {
			if(c.chochaste(bosses[i])) {
				for(int j=0; j<c.array().length;j++ )
					bosses[i].salirdelborde(c.array()[j]);
						}		
					}
				}
			}
			}

	public void mover(Exterminador e,Entorno entorno, Image aranas) {
		if(this!=null){
		 for(int i =0; i<bosses.length;i++) {
			 if(bosses[i]!=null) {	
				
				bosses[i].mover(e.angulo(bosses[i]));
				bosses[i].dibujar(entorno,aranas);

					
			 }
		 }
			 
	}

	}
	public boolean recibirDisparo( Disparar bala) {
	if(bala!=null&& this!=null) { 
		for(int i =0; i<bosses.length;i++) {
			 if(bosses[i]!=null) {
				 if(bala.bala()!=null && bala!=null){
					 if(bosses[i].recibedisparo(bala)) {
						disparosboss[i]+=1;
						 bala.hacernull();
					 
					 return true;
					 }
				 }
	}
		}
		}
		return false;
	}
	public Spider[] array() {
		return bosses;
	}
	public int[] get_arrayVidas() {
		return disparosboss;
	}
	public int arañaPisoMinaIndice(Campominado c) {
	if(this!=null){	
		int[] indices= new int[10];
		for(int i =0; i<bosses.length;i++) {
			if(bosses[i]!=null) {
			int indicemina=c.minapisadaindice(bosses[i]);
			if(indicemina<bosses.length) {	
					return i;
				}
			}
		}
	}
		return 10000;
	}
	public void hacerNull(int araña) {
		bosses[araña]=null;
	}
	public boolean arañaTocoJugador(Exterminador e) {
		if(e!=null) {
		for(int i =0; i<bosses.length;i++) {
			if(bosses[i]!=null) {
				if(e.perdiste(bosses[i])){
					return true;
				}
			}
		}
		}
		return false;
	}
	public void aparecerBosses(Entorno entorno, Ciudad ciudad,Exterminador one,int puntaje,int puntajeDeAparicion,int bossmuertos,Image imagen ){
		if(one!=null&& this!=null){
		if (puntaje>=puntajeDeAparicion &&  bossmuertos<bosses.length){
		for(int i=0; i<bosses.length;i++){
			if(bosses[i]!=null){
				bosses[i].vidaboss(disparosboss[i],entorno,10);
				bosses[i].dibujarboss(entorno,imagen);
				bosses[i].mover(one.angulo(bosses[i]));
	
			}
		}
		}
	}
	}
	
	//Retorna la cuenta de los bosses muertos tomando como parametro el entero del puntaje en el que aparecen
	public int contarBossesMuertos(int puntaje,int bossmuertos,int puntajeEnelqueAparecen){
		int contar=0;
	if (puntaje>=puntajeEnelqueAparecen &&  bossmuertos<bosses.length){	
		for(int i=0; i<bosses.length;i++){	
			if(bosses[i]==null) {
				contar++;
			}
		}
	}
	return contar;
	}
	
	//Aumenta en 1, si el objeto "Spider" colisiona con una "Mine", el entero correspondiente al array que almacena la cantidad de lesiones
	public void pisarMinas(Exterminador e, Campominado campo){
	if(this!=null){	
		for(int i=0; i<bosses.length;i++){	
			if(bosses[i]!=null ) {
				if(campo!=null) {
				if(campo.indiceminapisada(bosses[i])<1000){
						disparosboss[i]+=1;
				}
				}
				}
			}
			
		}
	}
	public Punto controlarVida(){
		Punto p = new Punto();
	if(this!=null){	
		for(int i=0; i<bosses.length; i++){
			if(bosses[i]!=null){
			if (disparosboss[i]>=10) {	
				p.x=bosses[i].get_X();
				p.y=bosses[i].get_Y();
			
				bosses[i]=null;
				
				return p;
		}
			}
		}
	}
		p=null;	
		return p;
	}
}
	
	
	

