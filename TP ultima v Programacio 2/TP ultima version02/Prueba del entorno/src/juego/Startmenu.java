package juego;

import java.awt.Color;

import entorno.Entorno;

public class Startmenu {
	private String[] leyendas;
	private int[] seleccionado;
	
	public Startmenu(int cantidadDeCosas){
		this.leyendas=new String[cantidadDeCosas];
		this.seleccionado= new int[cantidadDeCosas];
		for(int i =0; i<seleccionado.length; i++) {
				seleccionado[i]=0;
		}
	}
	public void agregarString(String leyenda){
		for(int i=0; i<leyendas.length;i++){
			if (leyendas[i]==null){
				leyendas[i]=leyenda;
				break;
			}
		}
	}

	public String[] strings() {
		return this.leyendas;
	}
	public void mover(Entorno e) {
		if (e.sePresiono(e.TECLA_ABAJO)) {
			for(int i =0; i<seleccionado.length-1; i++) {
				if (seleccionado[i]==1 && i<seleccionado.length) {
					seleccionado[i]=0;
					seleccionado[i+1]=1;
					break;
				}
			}	
		}
		if (e.sePresiono(e.TECLA_ARRIBA)) {
			for(int i =0; i<seleccionado.length; i++) {
				if(i>0 && seleccionado[i]==1 ) {
					seleccionado[i]=0;
					seleccionado[i-1]=1;
					break;
				}
			}
		}
	}
	public void dibujar(Entorno e, int xinicial,int yinicial) {
		int x = xinicial;
		int y = yinicial;
		for(int i=0; i<leyendas.length;i++){
			y+=50;
			if (leyendas[i]!=null && seleccionado[i]!=1){
				e.cambiarFont("comic sans MS", 50, Color.GREEN);
				e.escribirTexto(leyendas[i], x, y);
				
			}
			if (leyendas[i]!=null && seleccionado[i]==1) {
				
				e.cambiarFont("comic sans MS", 50, Color.RED);
				e.escribirTexto(leyendas[i], x, y);
			}
	}
	}
	public int seleccion(Entorno e) {
		if(e.sePresiono(e.TECLA_ENTER)) {
			for(int i =0; i<seleccionado.length; i++) {
				if(seleccionado[i]==1) {
					return i;
					
				}
			}
		}
		return 3000; 
	}
	public int seleccionniveles(Entorno e) {
		if(e.sePresiono(e.TECLA_ESPACIO)) {
			for(int i =0; i<seleccionado.length; i++) {
				if(seleccionado[i]==1) {
					seleccionado[i]=0;
					return i;
					
				}
			}
		}
		return 3000; 
	}
	public void iniciarmenu(Entorno e) {
		int cont =0;
		for(int i =0; i<seleccionado.length; i++) {
			if(seleccionado[i]==0) {
				cont++;
			}
		}
		if (cont == seleccionado.length) {
			if(e.sePresiono(e.TECLA_ABAJO)) {
				seleccionado[0]=1;
			}
		}
	}
	public void volvermenuaoriginal() {
		for(int i =0; i<seleccionado.length; i++) {
			seleccionado[i]=0;
		
		}
	}
}
