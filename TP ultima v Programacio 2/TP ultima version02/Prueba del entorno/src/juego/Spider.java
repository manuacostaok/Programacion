package juego;

import java.awt.Color;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Spider {
	 private double x;
	 private double y;
	 private double angulo;
	 private double velocidad;
	 private double factorDesplazamiento;
	 private double radio;


	
	
	 
	 public Spider(double x, double y, double angulo, double velocidad, double f) {
		 this.x=x;
		 this.y=y;
		 this.angulo=angulo;
		 this.velocidad=velocidad;
		 double factorDesplazamiento=f;

		 this.radio=10;
		
	 }
	 public Spider(Entorno e) {
		
		 Punto a = new Punto(50,e.alto()-100), b= new Punto (e.ancho()-100,50), f= new Punto(50,50), d= new Punto(e.alto()-100,e.ancho()-100);
		 Punto[] spawn= {a,b,f,d};
		 Random generador = new Random ();
		
			int n = generador.nextInt(4);
			
			 this.x=spawn[n].x;
			 this.y=spawn[n].y;
			 this.angulo=0;
			 this.velocidad=velocidadrandom();
			 this.radio=20;
			 this.factorDesplazamiento=4;
			
	 }
	 public Spider(Entorno e,int Boss) {
		

		 Punto a = new Punto(50,e.alto()-100), b= new Punto (e.ancho()-100,50), f= new Punto(50,50), d= new Punto(e.alto()-100,e.ancho()-100);
		 Punto[] spawn= {a,b,f,d};
		 Random generador = new Random ();
		
			int n = generador.nextInt(4);
			
			 this.x=spawn[n].x;
			 this.y=spawn[n].y;
			 this.angulo=0;
			 this.velocidad=velocidadrandom();
			
			 this.factorDesplazamiento=4;
			 this.radio=20;
		
		 }
	 
	 public void dibujar(Entorno e, Image arana) {
		 e.dibujarImagen(arana, x, y, angulo+90,0.25);
	 }
	 
	 public void dibujarboss(Entorno e, Image imagen) {
	
		 e.dibujarImagen(imagen, x, y, angulo+90,0.5);
	 }
	
	
	 public void mover(double angulo) {
		
			y -= velocidad * Math.sin(angulo);
			x -= velocidad * Math.cos(angulo);
			this.angulo=angulo;
		
		}
	 public double get_angulo() {
		 return angulo;
	 }
	 public double get_X(){
		 return x;
	 }
	 public double get_velocidad() {
		 return velocidad;
	 }
	 public double get_factorDesp() {
		 return factorDesplazamiento;
	 }
	 public double get_Y(){
		 return y;
	 }
	 public double get_radio() {
		 return radio;
	 }
	 
	//se fija si colisionaste con el borde de un edificio devolviendo verdadero o falso
	 public boolean chocaste(Edificio e) {
			if (this.x + (this.radio) > e.obtenerx() - (e.obtenerancho()/2) 
					&& this.x - (this.radio) < e.obtenerx() + e.obtenerancho()/2
					&& y + (this.radio) > e.obtenery() - (e.obteneralto()/2)
					&& y - (this.radio) < e.obtenery() + (e.obteneralto())/2) {
				
				return true;
			} 
		
			return false;
		}
	 
	public Punto punto() {
		Punto a = new Punto(x,y);
		return a;
	}
	
	//devuelve verdadero si colisiona un disparo con el objeto
	 public boolean recibedisparo(Disparar e) {
		  
			if(x+this.radio>e.bala().get_x()-(e.bala().get_ancho()) && x+this.radio< e.bala().get_x()+(e.bala().get_ancho())&& y+this.radio>e.bala().get_y()-(e.bala().get_alto()) && y<e.bala().get_y()+(e.bala().get_alto()) ) {
				
				return true;
		 }
		 
			return false;
	 }
	 
	 //devuelve verdadero si colisiona con un objeto "Mine"
	 public boolean pisamina(Mine e) {
			if(x+radio>e.get_x()-(e.get_radio()) && x-radio< e.get_x()+(e.get_radio()) && y-radio>e.get_y()-(e.get_radio()) && y+radio<e.get_y()+(e.get_radio()) ) {
				
				return true;
		 }
			return false;
	 }
	 
	 //	devuelve la distancia de una mina 
	public double distancia(Mine m) {

			 double d;
			 d = Math.sqrt(Math.pow(this.x-m.get_x(), 2)+Math.pow(this.y-m.get_y(), 2));
			 d -= (this.radio+m.get_radio());
			 if (d>1) {
				 return d;
				 
			 }
			 return 0.0;
		}
	//genera una velocidad random para la araña
	public double velocidadrandom() {
		 Random generador = new Random ();
		 double[] velocidades= {0.3,0.09,0.2,0.1,0.08};
		 int v= generador.nextInt(velocidades.length-1);
	return velocidades[v]; 
	}
	
	//Calcula la distancia entre dos arañas
	public double distancia(Spider spider) {
		 double d;
		 d = Math.sqrt(Math.pow(this.x-spider.get_X(), 2)+Math.pow(this.y-spider.get_Y(), 2));
		 d -= (this.radio+spider.get_radio());
		 if (d>0) {
			 return d;
			 
		 }
		 return 0.0;
	}
//dibuja la cantidad de vidas de "ArasGigantes"
	public void vidaboss(int disparos,Entorno e, int cantvidas) {
		
			double x = this.x;
			int vidas= cantvidas-disparos;
			for(int i=0; i<vidas;i++) {
				
		 e.dibujarRectangulo(x-5, this.y-30,5, 5, 0, Color.RED);
		  x+=6;
	  }
		}
	//Corre a la araña de los edificios
	public void salirdelborde(Edificio e) {
		if(this.x-radio>e.obtenerx()-e.obtenerancho()/2) {
			x-=radio/2;
		}
		if(this.x-radio<e.obtenerx()+e.obtenerancho()/2) {
			x+=radio/2;
		}
		if(this.y-radio<e.obtenery()-e.obteneralto()/2) {
			y-=radio/2;
		}
		if(this.y+radio>e.obtenery()+e.obteneralto()/2) {
			y+=radio/2;
		}
	}
	
	
	
}
