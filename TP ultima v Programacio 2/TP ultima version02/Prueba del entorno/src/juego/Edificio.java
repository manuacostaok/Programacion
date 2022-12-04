package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import entorno.Entorno;

public class Edificio {
	private double x;
	private double y;
	private int ancho;
	private int alto;
	

	public Edificio(double x, double y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}

	public Edificio() {

		Random generador = new Random();
		int x = generador.nextInt(700), y = generador.nextInt(500);
		//int alto = 0, ancho = 0;
		/*while (alto < 100 && ancho < 100) {
			/*alto = generador.nextInt(200);
			ancho = generador.nextInt(200);
		}*/
		this.x = x;
		this.y = y;
		this.ancho = 100;
		this.alto = 100;

	}

	public void dibujar(Entorno e, Image edificios) {
		e.dibujarRectangulo(x, y, ancho, alto, 0, Color.BLUE);
		e.dibujarImagen(edificios,this.x, this.y, 0);
	}


	public double obtenerx() {
		return this.x;
	}

	public double obtenery() {
		return this.y;
	}

	public double obteneralto() {
		return this.alto;
	}

	public double obtenerancho() {
		return this.ancho;
	}

	
//este metodo toma un edificio con el otro y se fija si se superponen devolviendo verdadero o falso
	public boolean chocaste(Edificio e) {
		if (this.x + (this.ancho) > e.obtenerx() - (e.obtenerancho()) 
				&& this.x - (this.ancho) < e.obtenerx() + e.obtenerancho()/2
				&& y + (this.alto) > e.obtenery() - (e.obteneralto())/2
				&& y - (this.alto) < e.obtenery() + (e.obteneralto())/2) {
			
			return true;
		} 
	
		return false;
	}


	
	

	
}
