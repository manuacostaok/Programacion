package juego;

import java.awt.Color;

import java.awt.Image;


import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;

	// Variables y métodos propios de cada grupo
	private Control ctrl;
	private int tiempo;
	private Image fondo;
	private Image tela;
	private int puntaje;
	private Image aranas;
	private Image arasgrandes;
	private Image edificios;
	private Image disparo;
	private Image fondoMenu;
	private Image exter;
	public Juego() {
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "invasion", 800, 600);
		this.tiempo = 0;
		 fondo = Herramientas.cargarImagen("fondo2.jpg");
		this.ctrl= new Control();
		aranas = Herramientas.cargarImagen("arascomunes.gif");
		edificios= Herramientas.cargarImagen("eificios2.png");
		tela = Herramientas.cargarImagen("telarañazul.png");
		this.arasgrandes = Herramientas.cargarImagen("esa.gif");
		 disparo= Herramientas.cargarImagen("disparo.png");
		 this.fondoMenu=Herramientas.cargarImagen("img001.jpg");
		 this.exter= Herramientas.cargarImagen("BillRizerRuns.gif");
		this.entorno.iniciar();
	}
	

	public void tick() {
		tiempo++;
		

		/*if(entorno.estaPresionada('q'))
			System.exit(0);*/
		if (ctrl.getMenu()) {
			entorno.dibujarImagen(fondoMenu, 400, 300, 0,2);
			int jugadores=0;
			jugadores=ctrl.menuCantjugadores(tiempo,entorno);
			ctrl.niveles(jugadores,entorno);
			}
			
		 else{
		
			principal();}	
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		Juego juego = new Juego();
	}
	public void principal() {
		ctrl.getMenuCantidadJugadores();
		ctrl.setmenu(false);
		entorno.dibujarImagen(fondo, 400, 300, 0);
		ctrl.dibujaredificios(entorno,edificios);
		ctrl.interaccionjugador1ConMundo(entorno, tiempo,exter);
		ctrl.setJugadorControles(entorno,'w', 's', 'd', 'a',1);
		ctrl.setDispararConTeclaEspacio(entorno, disparo, 1);
		ctrl.interaccionjugador2( entorno, tiempo,exter);
		ctrl.setTeclaDisparo(2, 'l', entorno, disparo);
		ctrl.setJugadorControlFlechas(entorno,2);
		ctrl.jugadoresPonerMinas(entorno,'m', 'p');
		ctrl.dibujarYMoverAranias(entorno,arasgrandes, aranas);
		ctrl.telarañasaparecer(tiempo, entorno,tela);
		ctrl.dibujarMinas(entorno);
		ctrl.controlarVidaDeBosses(entorno);
		puntaje = ctrl.GetPuntaje();
	
		entorno.cambiarFont("arial", 20, Color.CYAN);
		entorno.escribirTexto("vidas", 50, 50);
		entorno.escribirTexto("Puntaje", 50, 70);
		entorno.escribirTexto(Integer.toString(puntaje), 120, 70);
		entorno.escribirTexto("minas", 50, 90);
		entorno.escribirTexto(Integer.toString(30 - ctrl.getMinasJug1()), 120, 90);
		ctrl.controlarVidaJugadores(tiempo, entorno);
		this.entorno.iniciar();
	}

	

}
