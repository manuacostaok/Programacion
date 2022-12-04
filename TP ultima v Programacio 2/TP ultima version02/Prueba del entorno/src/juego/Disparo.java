package juego;


import java.awt.Image;
import entorno.Entorno;

public class Disparo {
	 private double disparox;
	 private double disparoy;
	 private double disparoangulo;
	 private double disparoVelocidad;
	 private double ancho;
	 private double alto;
	 
	 
	 public Disparo(double x, double y, double v, double a){
		 this.disparox=x;
		 this.disparoy=y;
		 this.disparoVelocidad=v;
		 this.disparoangulo=a;
		 this.alto=20;
		 this.ancho=20;
	 }
	
	 public void moverdisparo() {
		
		 
			disparoy += disparoVelocidad * Math.sin(disparoangulo);
			disparox += disparoVelocidad * Math.cos(disparoangulo);
			
		}
		 public void dibujardisparo(Entorno e, Image d) {
			
			 e.dibujarImagen(d, disparox, disparoy,disparoangulo,0.05);
			 //e.dibujarRectangulo(disparox, disparoy,alto, ancho, disparoa, Color.GREEN); 
		 }
	public void comienzodisparo(double x, double y, double angulo){
		disparox = x;
		disparoy=	y;
		disparoangulo=angulo;
	}
	public double get_x() {
		return this.disparox;
	}
	public double get_y() {
		return this.disparoy;
	}
	public double get_ancho() {
		return ancho;
	}
	public double get_alto() {
		return alto;
	}
	//Se fija si el "Disparo" colisiona con un "Edificio"
	 public boolean chocaste(Edificio e) {
			if(this.disparox+this.ancho/2>e.obtenerx()-(e.obtenerancho()/2) && this.disparox-ancho/2< e.obtenerx()+(e.obtenerancho()/2) && disparoy+(this.alto/2)>e.obtenery()-(e.obteneralto()/2) && disparoy-(this.alto/2)<e.obtenery()+(e.obteneralto()/2) ) {
				
				return true;
		 }
			return false;
	 }
}
