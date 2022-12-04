package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;

public class Telaraña {
	private double x;
	private double y;
	private double radio;
	private int tiempo;
	

	public Telaraña(double x, double y, double radio, int tiempo) {
		this.x=x;
		this.y=y;
		this.radio=radio;
		this.tiempo=tiempo+1000;
	}
	public Telaraña(Enjambre e,int tiempo) {
		Random generador = new Random ();
		int indice = generador.nextInt(e.get_arrayArañas().length-1);
		if(e.get_arrayArañas()[indice]!=null) {
		this.x= e.get_arrayArañas()[indice].get_X();
		this.y= e.get_arrayArañas()[indice].get_Y();
		this.radio= 50;
		this.tiempo=tiempo+2000;
		}
		
	}
	
	public void dibujar(Entorno e, Image tela) {
		
		 e.dibujarImagen(tela, x, y, 0,0.15);
	}
	
	public double x() {
		return this.x;
	}
	public double y() {
		return this.y;
	}
	public double r() {
		return this.radio;
	}
	public int t() {
		return this.tiempo;
	}
}
