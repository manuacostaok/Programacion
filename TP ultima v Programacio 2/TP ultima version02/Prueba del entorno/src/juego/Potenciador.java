package juego;

import java.awt.Color;
import java.awt.Image;


import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Potenciador {
	
	private double posicionx;
	private double posiciony;
	private double alto;
	private double ancho;
	private double angulo;
	private Image potenciador;
	private boolean activo;
	private boolean se_activo;
	private int contPot;
	
	
	public int getContPot() {
		return contPot;
	}

	public void setContPot(int contPot) {
		this.contPot = contPot;
	}

	public int getAux() {
		return aux;
	}

	public void setAux(int aux) {
		this.aux = aux;
	}

	private int aux;
	public Potenciador(double alto, double ancho, double angulo, boolean activo) {
		//super();
		Random generador = new Random();
		int x = generador.nextInt(600), y = generador.nextInt(400);
		this.posicionx =x;
		this.posiciony = y;
		this.alto = alto;
		this.ancho = ancho;
		this.angulo=angulo;
		this.setActivo(activo);
		this.setSe_activo(false);
	}

	public void dibujar(Entorno e) {
		//this.potenciador=Herramientas.cargarImagen("potenciador.png");
		 e.dibujarRectangulo(this.posicionx, this.posiciony,this.alto, this.ancho,this.angulo, Color.GREEN);
		// e.dibujarImagen(potenciador, posicionx, posiciony, angulo, 0.9);
	}
	
	public double getPosicionx() {
		return posicionx;
	}

	public void setPosicionx(int posicionx) {
		this.posicionx = posicionx;
	}

	public double getPosiciony() {
		return posiciony;
	}

	public void setPosiciony(int posiciony) {
		this.posiciony = posiciony;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	@Override
	public String toString() {
		return "Potenciador [posicionx=" + posicionx + ", posiciony="
				+ posiciony + ", alto=" + alto + ", ancho=" + ancho + "]";
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public boolean getSe_activo() {
		return se_activo;
	}

	public void setSe_activo(boolean se_activo) {
		this.se_activo = se_activo;
	}
	
	
	

}
