package juego;

import entorno.Entorno;
import java.awt.Color;

public class Mine {
	private double x;
	private double y;
	private double radio;

	
	public Mine(double x, double y) { 
		this.x=x;
		this.y=y;
		this.radio=40;
	}


	public void dibujar(Entorno e) {
		e.dibujarCirculo(x, y, 10 , Color.CYAN);
	}
	public double get_x() {
		return x;
	}
	public double get_y() {
		return y;
	}
	public double get_radio() {
		return radio;
	}
	
	//Devuelve al distancia a una mina 
	public double distancia(Mine m) {

		 double distancia;
		 distancia= Math.sqrt(Math.pow(this.x-m.get_x(), 2)+Math.pow(this.y-m.get_y(), 2));
		 distancia-=(this.radio+m.get_radio());
		 if (distancia>1){
			 return distancia;
			 
		 }
		 return 0.0;
	}
	
	//Devuelve verdadero si la mina recibe un disparo
	 public boolean recibedisparo(Disparar e) {
		  if(e.bala()!=null && this!=null && e!=null) {
			 
			  if (this.x + (this.radio) > e.bala().get_x() - (e.bala().get_ancho()/2) 
						&& this.x - (this.radio) < e.bala().get_x() +e.bala().get_ancho()/2
						&& y + (this.radio) > e.bala().get_y() - (e.bala().get_alto()/2)
						&& y - (this.radio) < e.bala().get_y() + (e.bala().get_alto())/2) {
					
					return true;
				} 
		  	}
			
				return false;
			
	 }

}
