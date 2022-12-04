package juego;



import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;

public class Exterminador {
  private double x;
  private double y;
  private double angulo;
  private double velocidad;
  private double factorDesplazamiento;
  private double radio;
  private double alto;
  private double ancho;
  private int vidas;
  private double veloRealentizada;
  private double velocidadOriginal;
  
  public Exterminador(double x, double y, double angulo, double velocidad,double f){
	  this.x= x;
	  this.y=y;
	  this.angulo=angulo;
	  this.velocidad=velocidad;
	  this.factorDesplazamiento=f;
	  radio=8;
	  this.ancho=10;
	  this.alto=15;
	  this.vidas=3;
	  this.veloRealentizada =this.velocidad - (this.velocidad*50)/100;
	  this.velocidadOriginal= velocidad;
  
  }
  
  public void dibujar(Entorno e, Image exter) {
	  if(this!=null) {
		  e.dibujarImagen(exter, x, y, angulo,0.2);
		  //e.dibujarRectangulo(x, y,10, 15, angulo, Color.RED);
	  }
  }
  
  public void moverIzq() {
		x -= factorDesplazamiento;
	}
  public double get_x(){
		return this.x;
	}

	public void moverDerecha() {
		x += factorDesplazamiento;
	}
	public void moveranguloder(){
		angulo+=0.02;
	}
	public void moveranguloizq() {
		angulo-=0.02;
	}
	public void avanzar() {
		y += velocidad * Math.sin(angulo);
		x += velocidad * Math.cos(angulo);
	}
	public void retroceder() {
		y -= (velocidad/2) * Math.sin(angulo);
		x -= (velocidad/2) * Math.cos(angulo);
	}
	public double get_y() {
		return this.y;
	}
	//Devuelve verdadero si colisiona con un edificio 
	public boolean chocaste(Edificio e) {
		if (this.x + (this.ancho) > e.obtenerx() - (e.obtenerancho()/2) 
				&& this.x - (this.ancho) < e.obtenerx() + e.obtenerancho()/2
				&& y + (this.alto) > e.obtenery() - (e.obteneralto()/2)
				&& y - (this.alto) < e.obtenery() + (e.obteneralto())/2) {
			
			return true;
		} 
	
		return false;
	}
	
	//Devuelve verdadero si colisiona con un objeto "Spider"
	public boolean perdiste(Spider e) {
	if (e!=null) {	
		if(x+radio>e.get_X()-(e.get_radio()) && x-radio< e.get_X()+(e.get_radio())&& y+radio>e.get_Y()-(e.get_radio()) && y<e.get_Y()+(e.get_radio()) ) {
			
			return true;
		}	
	 }
	return false;	
	}
	
	public double get_angulo() {
		return this.angulo;
	}
	public boolean chocasteCon(Entorno e) {
		return x <= 10 || x >= e.ancho() - 10 || y >= e.alto() - 15 || y - 15 <=0;		
	}
	
	 public Punto puntop() {
			Punto a = new Punto(x,y);
			return a;
	 }
	 //Reaparece al jugador en un espacio distinto de los edificios
	 public void spawn(Ciudad ciudad) {
		 Random generador = new Random ();
		 x= generador.nextInt(500);
		 y=generador.nextInt(400);
		if(ciudad.chocar(this)==true) {
			this.spawn(ciudad);
		}
	 }
	 //devuelve el angulo a una araña
	 public double angulo(Spider s) {
			double alfa = Math.atan2(s.get_Y()-y, s.get_X()-x);
			
			return alfa; 
		 }
	 
	 //devuelve la distancia del objeto a una "Mine" 
	public double distancia(Mine m) {

			 double d;
			 d = Math.sqrt(Math.pow(this.x-m.get_x(), 2)+Math.pow(this.y-m.get_y(), 2));
			 d -= (this.radio+m.get_radio());
			 if (d>1) {
				 return d;
				 
			 }
			 return 0.0;
		}
	
	
	public double anguloe(Edificio e) {
			double alfa = Math.atan2(e.obtenery()-y, e.obtenerx()-x);		
			return alfa; 
		 }
	//resta vidas
	public void restarvida() {
		vidas--;
		
	}
	public int get_vidas() {
		return vidas;
	}
	public void realentizar() {
		this.velocidad= veloRealentizada;
		
	}
	public void velocidadnormal(){
		this.velocidad= velocidadOriginal;
	}
	//Devuelve verdadero si choca con "Telaraña"
	public boolean chocastecon(Telaraña t) {
		if (t!=null) {	
			if(x+radio>t.x()-(t.r()) && x-radio< t.x()+(t.r())&& y+radio>t.y()-(t.r()) && y<t.y()+(t.r()) ) {
				
				return true;
			}	
		 }
		return false;	
		}
	public double get_velocidad(){
		return velocidad; 
	}
	//Corre al jugador del borde de la pantalla
	public void salirdelborde(Entorno e) {
		if(this.x-radio<0) {
			x+=radio;
		}
		if(this.x+radio>e.ancho()) {
			x-=radio;
		}
		if(this.y-radio<0) {
			y+=radio;
		}
		if(this.y+radio>e.alto()) {
			y-=radio;
		}
		
	}
	
	//corre al jugador del borde del "Edificio"
	public void salirdelborde(Edificio e) {
		if(this.x-radio>e.obtenerx()-e.obtenerancho()/2) {
			x+=radio;
		}
		if(this.x+radio<e.obtenerx()+e.obtenerancho()/2) {
			x-=radio;
		}
		if(this.y-radio>e.obtenery()-e.obteneralto()/2) {
			y+=radio;
		}
		if(this.y+radio<e.obtenery()+e.obteneralto()/2) {
			y-=radio;
		}
		
	}
	
	
	//Devuelve verdadero si el objeto colisiona con  un disparo
	public boolean recibedisparo(Disparar e) {
		 
		if(x+this.radio>e.bala().get_x()-(e.bala().get_ancho()) && x+this.radio< e.bala().get_x()+(e.bala().get_ancho())&& y+this.radio>e.bala().get_y()-(e.bala().get_alto()) && y<e.bala().get_y()+(e.bala().get_alto()) ) {
			
			return true;
	 }
	 
		return false;
 }
	
	//devuelve verdadero si las vidas del objeto son negativas
	public boolean comprobarvidas() {
		if(this!=null) {
		if (vidas<0) {
			return true;
		}
		}
		return false;
	}

	public boolean chocasteConPotenciador(Potenciador e){//funcion q se usa en chocasteCon
		if (e==null){
			return false;
		}
		else{
			if(x+10>e.getPosicionx()-(e.getAncho()/2) && x-10< e.getPosicionx()+(e.getAncho()/2)&& y+15>e.getPosiciony()-(e.getAlto()/2) && y-15<e.getPosiciony()+(e.getAlto()/2) ) {
				
				return true;
			}
			else return false;
		}
	}
	public void Acelerar(){//funcion q cambia la velocidad cuando agarra el potenciador
		
			this.velocidad=2.5;
		
	}
}
